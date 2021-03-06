package features.domain.builders;

import com.domainlanguage.money.Money;
import com.domainlanguage.time.CalendarDate;
import com.domainlanguage.time.TimePoint;
import features.domain.PrimitivesC;
import java.util.List;
import joist.domain.builders.AbstractBuilder;
import joist.domain.builders.DefaultsContext;
import joist.domain.uow.UoW;

@SuppressWarnings("all")
public abstract class PrimitivesCBuilderCodegen extends AbstractBuilder<PrimitivesC> {

  public PrimitivesCBuilderCodegen(PrimitivesC instance) {
    super(instance);
  }

  @Override
  public PrimitivesCBuilder defaults() {
    try {
      DefaultsContext.push();
      if (day() == null) {
        day(CalendarDate.from(1970, 1, 1));
      }
      if (dollarAmount() == null) {
        dollarAmount(Money.dollars(0));
      }
      if (name() == null) {
        name("name");
      }
      if (timestamp() == null) {
        timestamp(TimePoint.from(0));
      }
      return (PrimitivesCBuilder) super.defaults();
    } finally {
      DefaultsContext.pop();
    }
  }

  public CalendarDate day() {
    return get().getDay();
  }

  public PrimitivesCBuilder day(CalendarDate day) {
    get().setDay(day);
    return (PrimitivesCBuilder) this;
  }

  public PrimitivesCBuilder with(CalendarDate day) {
    return day(day);
  }

  public Money dollarAmount() {
    return get().getDollarAmount();
  }

  public PrimitivesCBuilder dollarAmount(Money dollarAmount) {
    get().setDollarAmount(dollarAmount);
    return (PrimitivesCBuilder) this;
  }

  public PrimitivesCBuilder with(Money dollarAmount) {
    return dollarAmount(dollarAmount);
  }

  public Long id() {
    if (UoW.isOpen() && get().getId() == null) {
      UoW.flush();
    }
    return get().getId();
  }

  public PrimitivesCBuilder id(Long id) {
    get().setId(id);
    return (PrimitivesCBuilder) this;
  }

  public String name() {
    return get().getName();
  }

  public PrimitivesCBuilder name(String name) {
    get().setName(name);
    return (PrimitivesCBuilder) this;
  }

  public PrimitivesCBuilder with(String name) {
    return name(name);
  }

  public TimePoint timestamp() {
    return get().getTimestamp();
  }

  public PrimitivesCBuilder timestamp(TimePoint timestamp) {
    get().setTimestamp(timestamp);
    return (PrimitivesCBuilder) this;
  }

  public PrimitivesCBuilder with(TimePoint timestamp) {
    return timestamp(timestamp);
  }

  public PrimitivesC get() {
    return (features.domain.PrimitivesC) super.get();
  }

  @Override
  public PrimitivesCBuilder ensureSaved() {
    doEnsureSaved();
    return (PrimitivesCBuilder) this;
  }

  @Override
  public void delete() {
    PrimitivesC.queries.delete(get());
  }

  public static void deleteAll() {
    List<Long> ids = PrimitivesC.queries.findAllIds();
    for (Long id : ids) {
      PrimitivesC.queries.delete(PrimitivesC.queries.find(id));
    }
  }

}
