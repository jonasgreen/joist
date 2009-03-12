package features.domain;

import java.util.ArrayList;
import java.util.List;
import joist.domain.queries.Alias;
import joist.domain.queries.columns.AliasColumn;
import joist.domain.queries.columns.BooleanAliasColumn;
import joist.domain.queries.columns.IdAliasColumn;
import joist.domain.queries.columns.IntAliasColumn;
import joist.domain.queries.columns.StringAliasColumn;

public class PrimitivesAlias extends Alias<Primitives> {

    private final List<AliasColumn<Primitives, ?, ?>> columns = new ArrayList<AliasColumn<Primitives, ?, ?>>();
    public final BooleanAliasColumn<Primitives> flag = new BooleanAliasColumn<Primitives>(this, "flag", PrimitivesCodegen.Shims.flag);
    public final IdAliasColumn<Primitives> id = new IdAliasColumn<Primitives>(this, "id", PrimitivesCodegen.Shims.id);
    public final StringAliasColumn<Primitives> name = new StringAliasColumn<Primitives>(this, "name", PrimitivesCodegen.Shims.name);
    public final IntAliasColumn<Primitives> version = new IntAliasColumn<Primitives>(this, "version", PrimitivesCodegen.Shims.version);

    public PrimitivesAlias(String alias) {
        super(Primitives.class, "primitives", alias);
        this.columns.add(this.flag);
        this.columns.add(this.id);
        this.columns.add(this.name);
        this.columns.add(this.version);
    }

    public List<AliasColumn<Primitives, ?, ?>> getColumns() {
        return this.columns;
    }

    public IdAliasColumn<Primitives> getIdColumn() {
        return this.id;
    }

    public IntAliasColumn<Primitives> getVersionColumn() {
        return this.version;
    }

    public IdAliasColumn<Primitives> getSubClassIdColumn() {
        return null;
    }

}