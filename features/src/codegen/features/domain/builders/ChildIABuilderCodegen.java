package features.domain.builders;

import features.domain.ChildIA;
import features.domain.ParentI;
import java.util.List;
import joist.domain.builders.AbstractBuilder;
import joist.domain.builders.DefaultsContext;
import joist.domain.uow.UoW;

@SuppressWarnings("all")
public abstract class ChildIABuilderCodegen extends AbstractBuilder<ChildIA> {

  public ChildIABuilderCodegen(ChildIA instance) {
    super(instance);
  }

  @Override
  public ChildIABuilder defaults() {
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
      return (ChildIABuilder) super.defaults();
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

  public ChildIABuilder id(Long id) {
    get().setId(id);
    return (ChildIABuilder) this;
  }

  public ParentIBuilder parent() {
    if (get().getParent() == null) {
      return null;
    }
    return Builders.existing(get().getParent());
  }

  public ChildIABuilder parent(ParentI parent) {
    get().setParent(parent);
    return (ChildIABuilder) this;
  }

  public ChildIABuilder with(ParentI parent) {
    return parent(parent);
  }

  public ChildIABuilder parent(ParentIBuilder parent) {
    return parent(parent == null ? null : parent.get());
  }

  public ChildIABuilder with(ParentIBuilder parent) {
    return parent(parent);
  }

  public ChildIA get() {
    return (features.domain.ChildIA) super.get();
  }

  @Override
  public ChildIABuilder ensureSaved() {
    doEnsureSaved();
    return (ChildIABuilder) this;
  }

  @Override
  public void delete() {
    ChildIA.queries.delete(get());
  }

  public static void deleteAll() {
    List<Long> ids = ChildIA.queries.findAllIds();
    for (Long id : ids) {
      ChildIA.queries.delete(ChildIA.queries.find(id));
    }
  }

}
