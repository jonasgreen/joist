package features.domain.builders;

import features.domain.ParentBChildBar;
import features.domain.ParentBChildZaz;
import features.domain.ParentBParent;
import java.util.ArrayList;
import java.util.List;
import joist.domain.builders.AbstractBuilder;
import joist.domain.builders.DefaultsContext;
import joist.domain.uow.UoW;

@SuppressWarnings("all")
public abstract class ParentBChildBarBuilderCodegen extends AbstractBuilder<ParentBChildBar> {

  public ParentBChildBarBuilderCodegen(ParentBChildBar instance) {
    super(instance);
  }

  @Override
  public ParentBChildBarBuilder defaults() {
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
      return (ParentBChildBarBuilder) super.defaults();
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

  public ParentBChildBarBuilder id(Long id) {
    get().setId(id);
    return (ParentBChildBarBuilder) this;
  }

  public String name() {
    return get().getName();
  }

  public ParentBChildBarBuilder name(String name) {
    get().setName(name);
    return (ParentBChildBarBuilder) this;
  }

  public ParentBChildBarBuilder with(String name) {
    return name(name);
  }

  public ParentBParentBuilder parentBParent() {
    if (get().getParentBParent() == null) {
      return null;
    }
    return Builders.existing(get().getParentBParent());
  }

  public ParentBChildBarBuilder parentBParent(ParentBParent parentBParent) {
    get().setParentBParent(parentBParent);
    return (ParentBChildBarBuilder) this;
  }

  public ParentBChildBarBuilder with(ParentBParent parentBParent) {
    return parentBParent(parentBParent);
  }

  public ParentBChildBarBuilder parentBParent(ParentBParentBuilder parentBParent) {
    return parentBParent(parentBParent == null ? null : parentBParent.get());
  }

  public ParentBChildBarBuilder with(ParentBParentBuilder parentBParent) {
    return parentBParent(parentBParent);
  }

  public List<ParentBChildZazBuilder> parentBChildZazs() {
    List<ParentBChildZazBuilder> b = new ArrayList<ParentBChildZazBuilder>();
    for (ParentBChildZaz e : get().getParentBChildZazs()) {
      b.add(Builders.existing(e));
    }
    return b;
  }

  public ParentBChildZazBuilder parentBChildZaz(int i) {
    return Builders.existing(get().getParentBChildZazs().get(i));
  }

  public ParentBChildZazBuilder newParentBChildZaz() {
    return Builders.aParentBChildZaz().parentBChildBar((ParentBChildBarBuilder) this);
  }

  public ParentBChildBar get() {
    return (features.domain.ParentBChildBar) super.get();
  }

  @Override
  public ParentBChildBarBuilder ensureSaved() {
    doEnsureSaved();
    return (ParentBChildBarBuilder) this;
  }

  @Override
  public void delete() {
    ParentBChildBar.queries.delete(get());
  }

  public static void deleteAll() {
    List<Long> ids = ParentBChildBar.queries.findAllIds();
    for (Long id : ids) {
      ParentBChildBar.queries.delete(ParentBChildBar.queries.find(id));
    }
  }

}
