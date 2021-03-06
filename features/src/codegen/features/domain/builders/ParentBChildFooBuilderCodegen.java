package features.domain.builders;

import features.domain.ParentBChildFoo;
import features.domain.ParentBParent;
import java.util.List;
import joist.domain.builders.AbstractBuilder;
import joist.domain.builders.DefaultsContext;
import joist.domain.uow.UoW;

@SuppressWarnings("all")
public abstract class ParentBChildFooBuilderCodegen extends AbstractBuilder<ParentBChildFoo> {

  public ParentBChildFooBuilderCodegen(ParentBChildFoo instance) {
    super(instance);
  }

  @Override
  public ParentBChildFooBuilder defaults() {
    try {
      DefaultsContext.push();
      if (name() == null) {
        name("name");
      }
      DefaultsContext.get().rememberIfSet(parentBParent());
      if (parentBParent() == null) {
        parentBParent(DefaultsContext.get().getIfAvailable(ParentBParent.class));
        if (parentBParent() == null) {
          parentBParent(Builders.aParentBParent().defaults());
          DefaultsContext.get().rememberIfSet(parentBParent());
        }
      }
      return (ParentBChildFooBuilder) super.defaults();
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

  public ParentBChildFooBuilder id(Long id) {
    get().setId(id);
    return (ParentBChildFooBuilder) this;
  }

  public String name() {
    return get().getName();
  }

  public ParentBChildFooBuilder name(String name) {
    get().setName(name);
    return (ParentBChildFooBuilder) this;
  }

  public ParentBChildFooBuilder with(String name) {
    return name(name);
  }

  public ParentBParentBuilder parentBParent() {
    if (get().getParentBParent() == null) {
      return null;
    }
    return Builders.existing(get().getParentBParent());
  }

  public ParentBChildFooBuilder parentBParent(ParentBParent parentBParent) {
    get().setParentBParent(parentBParent);
    return (ParentBChildFooBuilder) this;
  }

  public ParentBChildFooBuilder with(ParentBParent parentBParent) {
    return parentBParent(parentBParent);
  }

  public ParentBChildFooBuilder parentBParent(ParentBParentBuilder parentBParent) {
    return parentBParent(parentBParent == null ? null : parentBParent.get());
  }

  public ParentBChildFooBuilder with(ParentBParentBuilder parentBParent) {
    return parentBParent(parentBParent);
  }

  public ParentBChildFoo get() {
    return (features.domain.ParentBChildFoo) super.get();
  }

  @Override
  public ParentBChildFooBuilder ensureSaved() {
    doEnsureSaved();
    return (ParentBChildFooBuilder) this;
  }

  @Override
  public void delete() {
    ParentBChildFoo.queries.delete(get());
  }

  public static void deleteAll() {
    List<Long> ids = ParentBChildFoo.queries.findAllIds();
    for (Long id : ids) {
      ParentBChildFoo.queries.delete(ParentBChildFoo.queries.find(id));
    }
  }

}
