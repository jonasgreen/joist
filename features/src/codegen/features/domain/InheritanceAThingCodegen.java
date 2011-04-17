package features.domain;

import features.domain.queries.InheritanceAThingQueries;
import java.util.List;
import joist.domain.AbstractChanged;
import joist.domain.AbstractDomainObject;
import joist.domain.Changed;
import joist.domain.Shim;
import joist.domain.orm.ForeignKeyListHolder;
import joist.domain.uow.UoW;
import joist.domain.validation.rules.MaxLength;
import joist.domain.validation.rules.NotNull;
import joist.util.Copy;

@SuppressWarnings("all")
public abstract class InheritanceAThingCodegen extends AbstractDomainObject {

    public static final InheritanceAThingQueries queries;
    private Long id = null;
    private String name = null;
    private Long version = null;
    private ForeignKeyListHolder<InheritanceAThing, InheritanceASubOne> inheritanceASubOnes = new ForeignKeyListHolder<InheritanceAThing, InheritanceASubOne>((InheritanceAThing) this, Aliases.inheritanceASubOne(), Aliases.inheritanceASubOne().inheritanceAThing);
    private ForeignKeyListHolder<InheritanceAThing, InheritanceASubTwo> inheritanceASubTwos = new ForeignKeyListHolder<InheritanceAThing, InheritanceASubTwo>((InheritanceAThing) this, Aliases.inheritanceASubTwo(), Aliases.inheritanceASubTwo().inheritanceAThing);
    protected Changed changed;

    static {
        Aliases.inheritanceAThing();
        queries = new InheritanceAThingQueries();
    }

    protected InheritanceAThingCodegen() {
        this.addExtraRules();
    }

    private void addExtraRules() {
        this.addRule(new NotNull<InheritanceAThing>(Shims.name));
        this.addRule(new MaxLength<InheritanceAThing>(Shims.name, 100));
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.getChanged().record("id", this.id, id);
        this.id = id;
        if (UoW.isOpen()) {
            UoW.getIdentityMap().store(this);
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.getChanged().record("name", this.name, name);
        this.name = name;
    }

    protected void defaultName(String name) {
        this.name = name;
    }

    public Long getVersion() {
        return this.version;
    }

    public List<InheritanceASubOne> getInheritanceASubOnes() {
        return this.inheritanceASubOnes.get();
    }

    public void setInheritanceASubOnes(List<InheritanceASubOne> inheritanceASubOnes) {
        for (InheritanceASubOne o : Copy.list(this.getInheritanceASubOnes())) {
            this.removeInheritanceASubOne(o);
        }
        for (InheritanceASubOne o : inheritanceASubOnes) {
            this.addInheritanceASubOne(o);
        }
    }

    public void addInheritanceASubOne(InheritanceASubOne o) {
        o.setInheritanceAThingWithoutPercolation((InheritanceAThing) this);
        this.addInheritanceASubOneWithoutPercolation(o);
    }

    public void removeInheritanceASubOne(InheritanceASubOne o) {
        o.setInheritanceAThingWithoutPercolation(null);
        this.removeInheritanceASubOneWithoutPercolation(o);
    }

    protected void addInheritanceASubOneWithoutPercolation(InheritanceASubOne o) {
        this.getChanged().record("inheritanceASubOnes");
        this.inheritanceASubOnes.add(o);
    }

    protected void removeInheritanceASubOneWithoutPercolation(InheritanceASubOne o) {
        this.getChanged().record("inheritanceASubOnes");
        this.inheritanceASubOnes.remove(o);
    }

    public List<InheritanceASubTwo> getInheritanceASubTwos() {
        return this.inheritanceASubTwos.get();
    }

    public void setInheritanceASubTwos(List<InheritanceASubTwo> inheritanceASubTwos) {
        for (InheritanceASubTwo o : Copy.list(this.getInheritanceASubTwos())) {
            this.removeInheritanceASubTwo(o);
        }
        for (InheritanceASubTwo o : inheritanceASubTwos) {
            this.addInheritanceASubTwo(o);
        }
    }

    public void addInheritanceASubTwo(InheritanceASubTwo o) {
        o.setInheritanceAThingWithoutPercolation((InheritanceAThing) this);
        this.addInheritanceASubTwoWithoutPercolation(o);
    }

    public void removeInheritanceASubTwo(InheritanceASubTwo o) {
        o.setInheritanceAThingWithoutPercolation(null);
        this.removeInheritanceASubTwoWithoutPercolation(o);
    }

    protected void addInheritanceASubTwoWithoutPercolation(InheritanceASubTwo o) {
        this.getChanged().record("inheritanceASubTwos");
        this.inheritanceASubTwos.add(o);
    }

    protected void removeInheritanceASubTwoWithoutPercolation(InheritanceASubTwo o) {
        this.getChanged().record("inheritanceASubTwos");
        this.inheritanceASubTwos.remove(o);
    }

    public InheritanceAThingChanged getChanged() {
        if (this.changed == null) {
            this.changed = new InheritanceAThingChanged((InheritanceAThing) this);
        }
        return (InheritanceAThingChanged) this.changed;
    }

    @Override
    public void clearAssociations() {
        super.clearAssociations();
        for (InheritanceASubOne o : Copy.list(this.getInheritanceASubOnes())) {
            o.setInheritanceAThingWithoutPercolation(null);
        }
        for (InheritanceASubTwo o : Copy.list(this.getInheritanceASubTwos())) {
            o.setInheritanceAThingWithoutPercolation(null);
        }
    }

    static class Shims {
        protected static final Shim<InheritanceAThing, Long> id = new Shim<InheritanceAThing, Long>() {
            public void set(InheritanceAThing instance, Long id) {
                ((InheritanceAThingCodegen) instance).id = id;
            }
            public Long get(InheritanceAThing instance) {
                return ((InheritanceAThingCodegen) instance).id;
            }
            public String getName() {
                return "id";
            }
        };
        protected static final Shim<InheritanceAThing, String> name = new Shim<InheritanceAThing, String>() {
            public void set(InheritanceAThing instance, String name) {
                ((InheritanceAThingCodegen) instance).name = name;
            }
            public String get(InheritanceAThing instance) {
                return ((InheritanceAThingCodegen) instance).name;
            }
            public String getName() {
                return "name";
            }
        };
        protected static final Shim<InheritanceAThing, Long> version = new Shim<InheritanceAThing, Long>() {
            public void set(InheritanceAThing instance, Long version) {
                ((InheritanceAThingCodegen) instance).version = version;
            }
            public Long get(InheritanceAThing instance) {
                return ((InheritanceAThingCodegen) instance).version;
            }
            public String getName() {
                return "version";
            }
        };
    }

    public static class InheritanceAThingChanged extends AbstractChanged {
        public InheritanceAThingChanged(InheritanceAThing instance) {
            super(instance);
        }
        public boolean hasId() {
            return this.contains("id");
        }
        public Long getOriginalId() {
            return (Long) this.getOriginal("id");
        }
        public boolean hasName() {
            return this.contains("name");
        }
        public String getOriginalName() {
            return (java.lang.String) this.getOriginal("name");
        }
        public boolean hasVersion() {
            return this.contains("version");
        }
        public Long getOriginalVersion() {
            return (Long) this.getOriginal("version");
        }
        public boolean hasInheritanceASubOnes() {
            return this.contains("inheritanceASubOnes");
        }
        public boolean hasInheritanceASubTwos() {
            return this.contains("inheritanceASubTwos");
        }
    }

}