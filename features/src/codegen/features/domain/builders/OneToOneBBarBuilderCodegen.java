package features.domain.builders;

import features.domain.OneToOneBBar;
import features.domain.OneToOneBFoo;
import java.util.List;
import joist.domain.builders.AbstractBuilder;
import joist.domain.builders.DefaultsContext;
import joist.domain.uow.UoW;

@SuppressWarnings("all")
public abstract class OneToOneBBarBuilderCodegen extends AbstractBuilder<OneToOneBBar> {

  public OneToOneBBarBuilderCodegen(OneToOneBBar instance) {
    super(instance);
  }

  @Override
  public OneToOneBBarBuilder defaults() {
    try {
      DefaultsContext.push();
      if (name() == null) {
        name("name");
      }
      DefaultsContext.get().rememberIfSet(oneToOneBFoo());
      if (oneToOneBFoo() == null) {
        oneToOneBFoo(DefaultsContext.get().getIfAvailable(OneToOneBFoo.class));
        if (oneToOneBFoo() == null) {
          oneToOneBFoo(Builders.aOneToOneBFoo().defaults());
          DefaultsContext.get().rememberIfSet(oneToOneBFoo());
        }
      }
      return (OneToOneBBarBuilder) super.defaults();
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

  public OneToOneBBarBuilder id(Long id) {
    get().setId(id);
    return (OneToOneBBarBuilder) this;
  }

  public String name() {
    return get().getName();
  }

  public OneToOneBBarBuilder name(String name) {
    get().setName(name);
    return (OneToOneBBarBuilder) this;
  }

  public OneToOneBBarBuilder with(String name) {
    return name(name);
  }

  public OneToOneBFooBuilder oneToOneBFoo() {
    if (get().getOneToOneBFoo() == null) {
      return null;
    }
    return Builders.existing(get().getOneToOneBFoo());
  }

  public OneToOneBBarBuilder oneToOneBFoo(OneToOneBFoo oneToOneBFoo) {
    get().setOneToOneBFoo(oneToOneBFoo);
    return (OneToOneBBarBuilder) this;
  }

  public OneToOneBBarBuilder with(OneToOneBFoo oneToOneBFoo) {
    return oneToOneBFoo(oneToOneBFoo);
  }

  public OneToOneBBarBuilder oneToOneBFoo(OneToOneBFooBuilder oneToOneBFoo) {
    return oneToOneBFoo(oneToOneBFoo == null ? null : oneToOneBFoo.get());
  }

  public OneToOneBBarBuilder with(OneToOneBFooBuilder oneToOneBFoo) {
    return oneToOneBFoo(oneToOneBFoo);
  }

  public OneToOneBBar get() {
    return (features.domain.OneToOneBBar) super.get();
  }

  @Override
  public OneToOneBBarBuilder ensureSaved() {
    doEnsureSaved();
    return (OneToOneBBarBuilder) this;
  }

  @Override
  public void delete() {
    OneToOneBBar.queries.delete(get());
  }

  public static void deleteAll() {
    List<Long> ids = OneToOneBBar.queries.findAllIds();
    for (Long id : ids) {
      OneToOneBBar.queries.delete(OneToOneBBar.queries.find(id));
    }
  }

}
