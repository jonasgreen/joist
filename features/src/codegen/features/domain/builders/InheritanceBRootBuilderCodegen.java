package features.domain.builders;

import features.domain.InheritanceBRoot;
import features.domain.InheritanceBRootChild;
import java.util.ArrayList;
import java.util.List;
import joist.domain.builders.AbstractBuilder;
import joist.domain.builders.DefaultsContext;
import joist.domain.uow.UoW;

@SuppressWarnings("all")
public abstract class InheritanceBRootBuilderCodegen extends AbstractBuilder<InheritanceBRoot> {

  public InheritanceBRootBuilderCodegen(InheritanceBRoot instance) {
    super(instance);
  }

  @Override
  public InheritanceBRootBuilder defaults() {
    try {
      DefaultsContext.push();
      if (name() == null) {
        name("name");
      }
      return (InheritanceBRootBuilder) super.defaults();
    } finally {
      DefaultsContext.pop();
    }
  }

  public Long id() {
    if (UoW.isOpen() && get().getId() == null) {
      UoW.flush();
    }
    return get().getId();
  }

  public InheritanceBRootBuilder id(Long id) {
    get().setId(id);
    return (InheritanceBRootBuilder) this;
  }

  public String name() {
    return get().getName();
  }

  public InheritanceBRootBuilder name(String name) {
    get().setName(name);
    return (InheritanceBRootBuilder) this;
  }

  public List<InheritanceBRootChildBuilder> inheritanceBRootChilds() {
    List<InheritanceBRootChildBuilder> b = new ArrayList<InheritanceBRootChildBuilder>();
    for (InheritanceBRootChild e : get().getInheritanceBRootChilds()) {
      b.add(Builders.existing(e));
    }
    return b;
  }

  public InheritanceBRootChildBuilder inheritanceBRootChild(int i) {
    return Builders.existing(get().getInheritanceBRootChilds().get(i));
  }

  public InheritanceBRootChildBuilder newInheritanceBRootChild() {
    return Builders.aInheritanceBRootChild().inheritanceBRoot((InheritanceBRootBuilder) this);
  }

  public InheritanceBRoot get() {
    return (features.domain.InheritanceBRoot) super.get();
  }

  @Override
  public InheritanceBRootBuilder ensureSaved() {
    doEnsureSaved();
    return (InheritanceBRootBuilder) this;
  }

  @Override
  public void delete() {
    InheritanceBRoot.queries.delete(get());
  }

  public static void deleteAll() {
    List<Long> ids = InheritanceBRoot.queries.findAllIds();
    for (Long id : ids) {
      InheritanceBRoot.queries.delete(InheritanceBRoot.queries.find(id));
    }
  }

}
