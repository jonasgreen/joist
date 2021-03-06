package features.domain.builders;

import features.domain.ChildIB;
import features.domain.ParentI;
import java.util.List;
import joist.domain.builders.AbstractBuilder;
import joist.domain.builders.DefaultsContext;
import joist.domain.uow.UoW;

@SuppressWarnings("all")
public abstract class ChildIBBuilderCodegen extends AbstractBuilder<ChildIB> {

  public ChildIBBuilderCodegen(ChildIB instance) {
    super(instance);
  }

  @Override
  public ChildIBBuilder defaults() {
    try {
      DefaultsContext.push();
      DefaultsContext.get().rememberIfSet(parent());
      if (parent() == null) {
        parent(DefaultsContext.get().getIfAvailable(ParentI.class));
        if (parent() == null) {
          parent(Builders.aParentI().defaults());
          DefaultsContext.get().rememberIfSet(parent());
        }
      }
      return (ChildIBBuilder) super.defaults();
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

  public ChildIBBuilder id(Long id) {
    get().setId(id);
    return (ChildIBBuilder) this;
  }

  public ParentIBuilder parent() {
    if (get().getParent() == null) {
      return null;
    }
    return Builders.existing(get().getParent());
  }

  public ChildIBBuilder parent(ParentI parent) {
    get().setParent(parent);
    return (ChildIBBuilder) this;
  }

  public ChildIBBuilder with(ParentI parent) {
    return parent(parent);
  }

  public ChildIBBuilder parent(ParentIBuilder parent) {
    return parent(parent == null ? null : parent.get());
  }

  public ChildIBBuilder with(ParentIBuilder parent) {
    return parent(parent);
  }

  public ChildIB get() {
    return (features.domain.ChildIB) super.get();
  }

  @Override
  public ChildIBBuilder ensureSaved() {
    doEnsureSaved();
    return (ChildIBBuilder) this;
  }

  @Override
  public void delete() {
    ChildIB.queries.delete(get());
  }

  public static void deleteAll() {
    List<Long> ids = ChildIB.queries.findAllIds();
    for (Long id : ids) {
      ChildIB.queries.delete(ChildIB.queries.find(id));
    }
  }

}
