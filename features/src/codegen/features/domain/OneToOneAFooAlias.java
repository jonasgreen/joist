package features.domain;

import java.util.ArrayList;
import java.util.List;
import joist.domain.DomainObject;
import joist.domain.queries.Alias;
import joist.domain.queries.JoinClause;
import joist.domain.queries.columns.AliasColumn;
import joist.domain.queries.columns.ForeignKeyAliasColumn;
import joist.domain.queries.columns.IdAliasColumn;
import joist.domain.queries.columns.IntAliasColumn;
import joist.domain.queries.columns.StringAliasColumn;

public class OneToOneAFooAlias extends Alias<OneToOneAFoo> {

    private final List<AliasColumn<OneToOneAFoo, ?, ?>> columns = new ArrayList<AliasColumn<OneToOneAFoo, ?, ?>>();
    public final IdAliasColumn<OneToOneAFoo> id = new IdAliasColumn<OneToOneAFoo>(this, "id", OneToOneAFooCodegen.Shims.id);
    public final StringAliasColumn<OneToOneAFoo> name = new StringAliasColumn<OneToOneAFoo>(this, "name", OneToOneAFooCodegen.Shims.name);
    public final IntAliasColumn<OneToOneAFoo> version = new IntAliasColumn<OneToOneAFoo>(this, "version", OneToOneAFooCodegen.Shims.version);

    public OneToOneAFooAlias(String alias) {
        super(OneToOneAFoo.class, "one_to_one_a_foo", alias);
        this.columns.add(this.id);
        this.columns.add(this.name);
        this.columns.add(this.version);
    }

    public JoinClause on(ForeignKeyAliasColumn<? extends DomainObject, OneToOneAFoo> on) {
        return new JoinClause("INNER JOIN", this, on);
    }

    public List<AliasColumn<OneToOneAFoo, ?, ?>> getColumns() {
        return this.columns;
    }

    public IdAliasColumn<OneToOneAFoo> getIdColumn() {
        return this.id;
    }

    public IntAliasColumn<OneToOneAFoo> getVersionColumn() {
        return this.version;
    }

    public IdAliasColumn<OneToOneAFoo> getSubClassIdColumn() {
        return null;
    }

}