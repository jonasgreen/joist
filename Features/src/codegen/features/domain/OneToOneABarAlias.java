package features.domain;

import features.domain.OneToOneABar;
import features.domain.OneToOneAFoo;
import java.util.ArrayList;
import java.util.List;
import org.exigencecorp.domainobjects.queries.Alias;
import org.exigencecorp.domainobjects.queries.columns.AliasColumn;
import org.exigencecorp.domainobjects.queries.columns.ForeignKeyAliasColumn;
import org.exigencecorp.domainobjects.queries.columns.IdAliasColumn;
import org.exigencecorp.domainobjects.queries.columns.IntAliasColumn;
import org.exigencecorp.domainobjects.queries.columns.StringAliasColumn;

public class OneToOneABarAlias extends Alias<OneToOneABar> {

    private final List<AliasColumn<OneToOneABar, ?, ?>> columns = new ArrayList<AliasColumn<OneToOneABar, ?, ?>>();
    public final IdAliasColumn<OneToOneABar> id = new IdAliasColumn<OneToOneABar>(this, "id", OneToOneABarCodegen.Shims.id);
    public final StringAliasColumn<OneToOneABar> name = new StringAliasColumn<OneToOneABar>(this, "name", OneToOneABarCodegen.Shims.name);
    public final IntAliasColumn<OneToOneABar> version = new IntAliasColumn<OneToOneABar>(this, "version", OneToOneABarCodegen.Shims.version);
    public final ForeignKeyAliasColumn<OneToOneABar, OneToOneAFoo> oneToOneAFoo = new ForeignKeyAliasColumn<OneToOneABar, OneToOneAFoo>(this, "one_to_one_a_foo_id", OneToOneABarCodegen.Shims.oneToOneAFooId);

    public OneToOneABarAlias(String alias) {
        super(OneToOneABar.class, "one_to_one_a_bar", alias);
        this.columns.add(this.id);
        this.columns.add(this.name);
        this.columns.add(this.version);
        this.columns.add(this.oneToOneAFoo);
    }

    public List<AliasColumn<OneToOneABar, ?, ?>> getColumns() {
        return this.columns;
    }

    public IdAliasColumn<OneToOneABar> getIdColumn() {
        return this.id;
    }

    public IntAliasColumn<OneToOneABar> getVersionColumn() {
        return this.version;
    }

    public IdAliasColumn<OneToOneABar> getSubClassIdColumn() {
        return null;
    }

}
