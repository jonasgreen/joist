package joist.domain.orm.queries;

import java.util.ArrayList;
import java.util.List;

import joist.util.Copy;

public class Where {

  private enum Type {
    AND, OR, SIMPLE, NOT
  };

  private final Clauses clauses = new Clauses();
  private final List<Object> parameters;
  private Type type = Type.SIMPLE;

  public static Where not(Where other) {
    Where w = new Where();
    w.clauses.add("NOT (");
    w.clauses.add(other.clauses);
    w.clauses.add(")");
    w.parameters.addAll(other.parameters);
    w.type = Type.NOT;
    return w;
  }

  private static Where make(String operator, Type newType, Where w1, Where w2) {
    boolean bothSimple = w1.type == Type.SIMPLE && w2.type == Type.SIMPLE;
    boolean moreSimple = w1.type == newType && w2.type == Type.SIMPLE;
    boolean firstSimple = w1.type == Type.SIMPLE && w2.type != Type.SIMPLE;

    Where w = new Where();
    if (bothSimple || moreSimple) {
      w.clauses.addNoIndent(w1.clauses);
      w.clauses.add(operator + " " + w2.getSql()); // simple
    } else if (firstSimple) {
      w.clauses.add(w1.getSql()); // simple
      w.clauses.add(operator + " (");
      w.clauses.add(w2.clauses);
      w.clauses.add(")");
    } else {
      w.clauses.add("(");
      w.clauses.add(w1.clauses);
      w.clauses.add(") " + operator + " (");
      w.clauses.add(w2.clauses);
      w.clauses.add(")");
    }
    w.type = newType;
    w.parameters.addAll(w1.getParameters());
    w.parameters.addAll(w2.parameters);
    return w;
  }

  private Where() {
    this.parameters = new ArrayList<Object>();
  }

  public Where(String sql, Object... parameters) {
    this.clauses.add(sql);
    this.parameters = Copy.list(parameters);
  }

  public Where(String sql, List<Object> parameters) {
    this.clauses.add(sql);
    this.parameters = parameters;
  }

  public Where and(Where other) {
    return Where.make("AND", Type.AND, this, other);
  }

  public Where or(Where other) {
    return Where.make("OR", Type.OR, this, other);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    this.clauses.append(sb);
    sb.deleteCharAt(0); // first space
    sb.deleteCharAt(sb.length() - 1); // last new line
    return sb.toString();
  }

  public List<Object> getParameters() {
    return this.parameters;
  }

  public String getSql() {
    return this.toString();
  }

  public String getSqlWithoutAliasPrefix(String aliasName) {
    return this.getSql().replaceAll(aliasName + "\\.", "");
  }

}
