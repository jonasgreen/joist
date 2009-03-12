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

public class ManyToManyAFooAlias extends Alias<ManyToManyAFoo> {

    private final List<AliasColumn<ManyToManyAFoo, ?, ?>> columns = new ArrayList<AliasColumn<ManyToManyAFoo, ?, ?>>();
    public final IdAliasColumn<ManyToManyAFoo> id = new IdAliasColumn<ManyToManyAFoo>(this, "id", ManyToManyAFooCodegen.Shims.id);
    public final StringAliasColumn<ManyToManyAFoo> name = new StringAliasColumn<ManyToManyAFoo>(this, "name", ManyToManyAFooCodegen.Shims.name);
    public final IntAliasColumn<ManyToManyAFoo> version = new IntAliasColumn<ManyToManyAFoo>(this, "version", ManyToManyAFooCodegen.Shims.version);

    public ManyToManyAFooAlias(String alias) {
        super(ManyToManyAFoo.class, "many_to_many_a_foo", alias);
        this.columns.add(this.id);
        this.columns.add(this.name);
        this.columns.add(this.version);
    }

    public List<AliasColumn<ManyToManyAFoo, ?, ?>> getColumns() {
        return this.columns;
    }

    public IdAliasColumn<ManyToManyAFoo> getIdColumn() {
        return this.id;
    }

    public IntAliasColumn<ManyToManyAFoo> getVersionColumn() {
        return this.version;
    }

    public IdAliasColumn<ManyToManyAFoo> getSubClassIdColumn() {
        return null;
    }

    public JoinClause on(ForeignKeyAliasColumn<? extends DomainObject, ManyToManyAFoo> on) {
        return new JoinClause("INNER JOIN", this, on);
    }

}