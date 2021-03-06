package features.domain.builders;

import features.domain.ParentCBar;
import features.domain.ParentCFoo;
import java.util.List;
import joist.domain.builders.AbstractBuilder;
import joist.domain.builders.DefaultsContext;
import joist.domain.uow.UoW;

@SuppressWarnings("all")
public abstract class ParentCBarBuilderCodegen extends AbstractBuilder<ParentCBar> {

  public ParentCBarBuilderCodegen(ParentCBar instance) {
    super(instance);
  }

  @Override
  public ParentCBarBuilder defaults() {
    try {
      DefaultsContext.push();
      if (name() == null) {
        name("name");
      }
      DefaultsContext.get().rememberIfSet(firstParent());
      DefaultsContext.get().rememberIfSet(secondParent());
      if (firstParent() == null) {
        firstParent(DefaultsContext.get().getIfAvailable(ParentCFoo.class));
        if (firstParent() == null) {
          firstParent(Builders.aParentCFoo().defaults());
          DefaultsContext.get().rememberIfSet(firstParent());
        }
      }
      if (secondParent() == null) {
        secondParent(DefaultsContext.get().getIfAvailable(ParentCFoo.class));
        if (secondParent() == null) {
          secondParent(Builders.aParentCFoo().defaults());
          DefaultsContext.get().rememberIfSet(secondParent());
        }
      }
      return (ParentCBarBuilder) super.defaults();
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

  public ParentCBarBuilder id(Long id) {
    get().setId(id);
    return (ParentCBarBuilder) this;
  }

  public String name() {
    return get().getName();
  }

  public ParentCBarBuilder name(String name) {
    get().setName(name);
    return (ParentCBarBuilder) this;
  }

  public ParentCBarBuilder with(String name) {
    return name(name);
  }

  public ParentCFooBuilder firstParent() {
    if (get().getFirstParent() == null) {
      return null;
    }
    return Builders.existing(get().getFirstParent());
  }

  public ParentCBarBuilder firstParent(ParentCFoo firstParent) {
    get().setFirstParent(firstParent);
    return (ParentCBarBuilder) this;
  }

  public ParentCBarBuilder firstParent(ParentCFooBuilder firstParent) {
    return firstParent(firstParent == null ? null : firstParent.get());
  }

  public ParentCFooBuilder secondParent() {
    if (get().getSecondParent() == null) {
      return null;
    }
    return Builders.existing(get().getSecondParent());
  }

  public ParentCBarBuilder secondParent(ParentCFoo secondParent) {
    get().setSecondParent(secondParent);
    return (ParentCBarBuilder) this;
  }

  public ParentCBarBuilder secondParent(ParentCFooBuilder secondParent) {
    return secondParent(secondParent == null ? null : secondParent.get());
  }

  public ParentCBar get() {
    return (features.domain.ParentCBar) super.get();
  }

  @Override
  public ParentCBarBuilder ensureSaved() {
    doEnsureSaved();
    return (ParentCBarBuilder) this;
  }

  @Override
  public void delete() {
    ParentCBar.queries.delete(get());
  }

  public static void deleteAll() {
    List<Long> ids = ParentCBar.queries.findAllIds();
    for (Long id : ids) {
      ParentCBar.queries.delete(ParentCBar.queries.find(id));
    }
  }

}
