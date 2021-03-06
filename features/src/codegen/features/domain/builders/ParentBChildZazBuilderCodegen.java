package features.domain.builders;

import features.domain.ParentBChildBar;
import features.domain.ParentBChildZaz;
import features.domain.ParentBParent;
import java.util.List;
import joist.domain.builders.AbstractBuilder;
import joist.domain.builders.DefaultsContext;
import joist.domain.uow.UoW;

@SuppressWarnings("all")
public abstract class ParentBChildZazBuilderCodegen extends AbstractBuilder<ParentBChildZaz> {

  public ParentBChildZazBuilderCodegen(ParentBChildZaz instance) {
    super(instance);
  }

  @Override
  public ParentBChildZazBuilder defaults() {
    try {
      DefaultsContext.push();
      if (name() == null) {
        name("name");
      }
      DefaultsContext.get().rememberIfSet(parentBChildBar());
      DefaultsContext.get().rememberIfSet(parentBParent());
      if (parentBChildBar() == null) {
        parentBChildBar(DefaultsContext.get().getIfAvailable(ParentBChildBar.class));
        if (parentBChildBar() == null) {
          parentBChildBar(Builders.aParentBChildBar().defaults());
          DefaultsContext.get().rememberIfSet(parentBChildBar());
        }
      }
      if (parentBParent() == null) {
        parentBParent(DefaultsContext.get().getIfAvailable(ParentBParent.class));
        if (parentBParent() == null) {
          parentBParent(Builders.aParentBParent().defaults());
          DefaultsContext.get().rememberIfSet(parentBParent());
        }
      }
      return (ParentBChildZazBuilder) super.defaults();
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

  public ParentBChildZazBuilder id(Long id) {
    get().setId(id);
    return (ParentBChildZazBuilder) this;
  }

  public String name() {
    return get().getName();
  }

  public ParentBChildZazBuilder name(String name) {
    get().setName(name);
    return (ParentBChildZazBuilder) this;
  }

  public ParentBChildZazBuilder with(String name) {
    return name(name);
  }

  public ParentBChildBarBuilder parentBChildBar() {
    if (get().getParentBChildBar() == null) {
      return null;
    }
    return Builders.existing(get().getParentBChildBar());
  }

  public ParentBChildZazBuilder parentBChildBar(ParentBChildBar parentBChildBar) {
    get().setParentBChildBar(parentBChildBar);
    return (ParentBChildZazBuilder) this;
  }

  public ParentBChildZazBuilder with(ParentBChildBar parentBChildBar) {
    return parentBChildBar(parentBChildBar);
  }

  public ParentBChildZazBuilder parentBChildBar(ParentBChildBarBuilder parentBChildBar) {
    return parentBChildBar(parentBChildBar == null ? null : parentBChildBar.get());
  }

  public ParentBChildZazBuilder with(ParentBChildBarBuilder parentBChildBar) {
    return parentBChildBar(parentBChildBar);
  }

  public ParentBParentBuilder parentBParent() {
    if (get().getParentBParent() == null) {
      return null;
    }
    return Builders.existing(get().getParentBParent());
  }

  public ParentBChildZazBuilder parentBParent(ParentBParent parentBParent) {
    get().setParentBParent(parentBParent);
    return (ParentBChildZazBuilder) this;
  }

  public ParentBChildZazBuilder with(ParentBParent parentBParent) {
    return parentBParent(parentBParent);
  }

  public ParentBChildZazBuilder parentBParent(ParentBParentBuilder parentBParent) {
    return parentBParent(parentBParent == null ? null : parentBParent.get());
  }

  public ParentBChildZazBuilder with(ParentBParentBuilder parentBParent) {
    return parentBParent(parentBParent);
  }

  public ParentBChildZaz get() {
    return (features.domain.ParentBChildZaz) super.get();
  }

  @Override
  public ParentBChildZazBuilder ensureSaved() {
    doEnsureSaved();
    return (ParentBChildZazBuilder) this;
  }

  @Override
  public void delete() {
    ParentBChildZaz.queries.delete(get());
  }

  public static void deleteAll() {
    List<Long> ids = ParentBChildZaz.queries.findAllIds();
    for (Long id : ids) {
      ParentBChildZaz.queries.delete(ParentBChildZaz.queries.find(id));
    }
  }

}
