package features.domain;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import features.domain.queries.PrimitivesQueries.NameAndFlag;

public class PrimitivesAggregatesTest extends AbstractFeaturesTest {

  @Test
  public void testCount() {
    new Primitives("count");
    this.commitAndReOpen();
    Assert.assertEquals(1, Primitives.queries.count());
  }

  @Test
  public void testCountWithConditions() {
    new Primitives("count");
    this.commitAndReOpen();

    Assert.assertEquals(1, Primitives.queries.countWhereFlagIs(false));
    Assert.assertEquals(0, Primitives.queries.countWhereFlagIs(true));
  }

  @Test
  public void testNameOnly() {
    new Primitives("testNameOnly");
    this.commitAndReOpen();
    Assert.assertEquals("testNameOnly", Primitives.queries.findNameOnly(1));
  }

  @Test
  public void testNameAndFlagOnly() {
    new Primitives("name1");
    new Primitives("name2");
    this.commitAndReOpen();

    List<NameAndFlag> dtos = Primitives.queries.findNameAndFlagOnly();
    Assert.assertEquals(2, dtos.size());
    Assert.assertEquals("name1", dtos.get(0).name);
    Assert.assertEquals("name2", dtos.get(1).name);
  }

}
