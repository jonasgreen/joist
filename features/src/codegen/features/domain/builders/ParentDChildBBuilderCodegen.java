package features.domain.builders;

import features.domain.ParentD;
import features.domain.ParentDChildB;
import java.util.List;
import joist.domain.builders.AbstractBuilder;
import joist.domain.builders.DefaultsContext;
import joist.domain.uow.UoW;

@SuppressWarnings("all")
public abstract class ParentDChildBBuilderCodegen extends AbstractBuilder<ParentDChildB> {

  public ParentDChildBBuilderCodegen(ParentDChildB instance) {
    super(instance);
  }

  @Override
  public ParentDChildBBuilder defaults() {
    try {
      DefaultsContext.push();
      if (name() == null) {
        name("name");
      }
      DefaultsContext.get().rememberIfSet(parentD());
      if (parentD() == null) {
        parentD(DefaultsContext.get().getIfAvailable(ParentD.class));
        if (parentD() == null) {
          parentD(Builders.aParentD().defaults());
          DefaultsContext.get().rememberIfSet(parentD());
        }
      }
      return (ParentDChildBBuilder) super.defaults();
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

  public ParentDChildBBuilder id(Long id) {
    get().setId(id);
    return (ParentDChildBBuilder) this;
  }

  public String name() {
    return get().getName();
  }

  public ParentDChildBBuilder name(String name) {
    get().setName(name);
    return (ParentDChildBBuilder) this;
  }

  public ParentDChildBBuilder with(String name) {
    return name(name);
  }

  public ParentDBuilder parentD() {
    if (get().getParentD() == null) {
      return null;
    }
    return Builders.existing(get().getParentD());
  }

  public ParentDChildBBuilder parentD(ParentD parentD) {
    get().setParentD(parentD);
    return (ParentDChildBBuilder) this;
  }

  public ParentDChildBBuilder with(ParentD parentD) {
    return parentD(parentD);
  }

  public ParentDChildBBuilder parentD(ParentDBuilder parentD) {
    return parentD(parentD == null ? null : parentD.get());
  }

  public ParentDChildBBuilder with(ParentDBuilder parentD) {
    return parentD(parentD);
  }

  public ParentDChildB get() {
    return (features.domain.ParentDChildB) super.get();
  }

  @Override
  public ParentDChildBBuilder ensureSaved() {
    doEnsureSaved();
    return (ParentDChildBBuilder) this;
  }

  @Override
  public void delete() {
    ParentDChildB.queries.delete(get());
  }

  public static void deleteAll() {
    List<Long> ids = ParentDChildB.queries.findAllIds();
    for (Long id : ids) {
      ParentDChildB.queries.delete(ParentDChildB.queries.find(id));
    }
  }

}
