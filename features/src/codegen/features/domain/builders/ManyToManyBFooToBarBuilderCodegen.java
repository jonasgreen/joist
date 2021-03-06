package features.domain.builders;

import features.domain.ManyToManyBBar;
import features.domain.ManyToManyBFoo;
import features.domain.ManyToManyBFooToBar;
import java.util.List;
import joist.domain.builders.AbstractBuilder;
import joist.domain.builders.DefaultsContext;
import joist.domain.uow.UoW;

@SuppressWarnings("all")
public abstract class ManyToManyBFooToBarBuilderCodegen extends AbstractBuilder<ManyToManyBFooToBar> {

  public ManyToManyBFooToBarBuilderCodegen(ManyToManyBFooToBar instance) {
    super(instance);
  }

  @Override
  public ManyToManyBFooToBarBuilder defaults() {
    try {
      DefaultsContext.push();
      DefaultsContext.get().rememberIfSet(owned());
      DefaultsContext.get().rememberIfSet(ownerManyToManyBFoo());
      if (owned() == null) {
        owned(DefaultsContext.get().getIfAvailable(ManyToManyBBar.class));
        if (owned() == null) {
          owned(Builders.aManyToManyBBar().defaults());
          DefaultsContext.get().rememberIfSet(owned());
        }
      }
      if (ownerManyToManyBFoo() == null) {
        ownerManyToManyBFoo(DefaultsContext.get().getIfAvailable(ManyToManyBFoo.class));
        if (ownerManyToManyBFoo() == null) {
          ownerManyToManyBFoo(Builders.aManyToManyBFoo().defaults());
          DefaultsContext.get().rememberIfSet(ownerManyToManyBFoo());
        }
      }
      return (ManyToManyBFooToBarBuilder) super.defaults();
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

  public ManyToManyBFooToBarBuilder id(Long id) {
    get().setId(id);
    return (ManyToManyBFooToBarBuilder) this;
  }

  public ManyToManyBBarBuilder owned() {
    if (get().getOwned() == null) {
      return null;
    }
    return Builders.existing(get().getOwned());
  }

  public ManyToManyBFooToBarBuilder owned(ManyToManyBBar owned) {
    get().setOwned(owned);
    return (ManyToManyBFooToBarBuilder) this;
  }

  public ManyToManyBFooToBarBuilder with(ManyToManyBBar owned) {
    return owned(owned);
  }

  public ManyToManyBFooToBarBuilder owned(ManyToManyBBarBuilder owned) {
    return owned(owned == null ? null : owned.get());
  }

  public ManyToManyBFooToBarBuilder with(ManyToManyBBarBuilder owned) {
    return owned(owned);
  }

  public ManyToManyBFooBuilder ownerManyToManyBFoo() {
    if (get().getOwnerManyToManyBFoo() == null) {
      return null;
    }
    return Builders.existing(get().getOwnerManyToManyBFoo());
  }

  public ManyToManyBFooToBarBuilder ownerManyToManyBFoo(ManyToManyBFoo ownerManyToManyBFoo) {
    get().setOwnerManyToManyBFoo(ownerManyToManyBFoo);
    return (ManyToManyBFooToBarBuilder) this;
  }

  public ManyToManyBFooToBarBuilder with(ManyToManyBFoo ownerManyToManyBFoo) {
    return ownerManyToManyBFoo(ownerManyToManyBFoo);
  }

  public ManyToManyBFooToBarBuilder ownerManyToManyBFoo(ManyToManyBFooBuilder ownerManyToManyBFoo) {
    return ownerManyToManyBFoo(ownerManyToManyBFoo == null ? null : ownerManyToManyBFoo.get());
  }

  public ManyToManyBFooToBarBuilder with(ManyToManyBFooBuilder ownerManyToManyBFoo) {
    return ownerManyToManyBFoo(ownerManyToManyBFoo);
  }

  public ManyToManyBFooToBar get() {
    return (features.domain.ManyToManyBFooToBar) super.get();
  }

  @Override
  public ManyToManyBFooToBarBuilder ensureSaved() {
    doEnsureSaved();
    return (ManyToManyBFooToBarBuilder) this;
  }

  @Override
  public void delete() {
    ManyToManyBFooToBar.queries.delete(get());
  }

  public static void deleteAll() {
    List<Long> ids = ManyToManyBFooToBar.queries.findAllIds();
    for (Long id : ids) {
      ManyToManyBFooToBar.queries.delete(ManyToManyBFooToBar.queries.find(id));
    }
  }

}
