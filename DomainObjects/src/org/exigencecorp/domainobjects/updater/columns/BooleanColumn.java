package org.exigencecorp.domainobjects.updater.columns;

public class BooleanColumn extends AbstractColumn<BooleanColumn> {

    private Boolean defaultValue = null;

    public BooleanColumn(String name) {
        super(name);
    }

    public BooleanColumn defaultTrue() {
        this.defaultValue = Boolean.TRUE;
        return this;
    }

    public BooleanColumn defaultFalse() {
        this.defaultValue = Boolean.FALSE;
        return this;
    }

    public String toSql() {
        String sql = this.getName() + " boolean";
        if (this.defaultValue != null) {
            sql += " DEFAULT " + this.defaultValue.toString().toUpperCase() + ",";
        } else {
            sql += ",";
        }
        return sql;
    }

}
