package features.domain;

import java.util.List;

import junit.framework.Assert;

public class InheritanceATest extends AbstractFeaturesTest {

  public void testSaveInheritanceASubOne() {
    InheritanceASubOne a = new InheritanceASubOne();
    a.setName("name");
    a.setOne("one");
    this.commitAndReOpen();
    Assert.assertEquals(1, a.getId().intValue());

    a = this.reload(a);
    Assert.assertEquals("name", a.getName());
    Assert.assertEquals("one", a.getOne());
  }

  public void testInsertInheritanceASubTwo() {
    InheritanceASubTwo b = new InheritanceASubTwo();
    b.setName("name");
    b.setTwo("two");
    this.commitAndReOpen();
    Assert.assertEquals(1, b.getId().intValue());

    b = this.reload(b);
    Assert.assertEquals("name", b.getName());
    Assert.assertEquals("two", b.getTwo());
  }

  public void testUpdateInheritanceASubTwo() {
    InheritanceASubTwo b = new InheritanceASubTwo();
    b.setName("name");
    b.setTwo("two");
    this.commitAndReOpen();
    Assert.assertEquals(1, b.getId().intValue());

    b = this.reload(b);
    b.setName("name2");
    b.setTwo("twotwo");
    this.commitAndReOpen();

    b = this.reload(b);
    Assert.assertEquals("name2", b.getName());
    Assert.assertEquals("twotwo", b.getTwo());
  }

  public void testQueryOnBaseClassReturnsSubClasses() {
    new InheritanceASubOne("namea", "a");
    new InheritanceASubTwo("nameb", "b");
    this.commitAndReOpen();

    List<InheritanceABase> l = InheritanceABase.queries.findAll();
    Assert.assertEquals(2, l.size());
    Assert.assertEquals(InheritanceASubOne.class, l.get(0).getClass());
    Assert.assertEquals(InheritanceASubTwo.class, l.get(1).getClass());
    Assert.assertEquals("a", ((InheritanceASubOne) l.get(0)).getOne());
    Assert.assertEquals("b", ((InheritanceASubTwo) l.get(1)).getTwo());

    InheritanceASubOne otherA = InheritanceASubOne.queries.find(1);
    Assert.assertTrue(otherA == l.get(0));
  }

  public void testQueryOnSubClassReturnsOnlyThatSubClass() {
    new InheritanceASubOne("namea", "a");
    new InheritanceASubTwo("nameb", "b");
    this.commitAndReOpen();

    List<InheritanceASubOne> l = InheritanceASubOne.queries.findAll();
    Assert.assertEquals(1, l.size());
    Assert.assertEquals(InheritanceASubOne.class, l.get(0).getClass());

    InheritanceASubOne otherA = InheritanceASubOne.queries.find(1);
    Assert.assertTrue(otherA == l.get(0));

    InheritanceABase baseA = InheritanceABase.queries.find(1);
    Assert.assertTrue(otherA == baseA);
  }

  public void testQueryOnSubClassByBaseClassAttribute() {
    new InheritanceASubOne("namea", "a");
    new InheritanceASubTwo("nameb", "b");
    this.commitAndReOpen();

    InheritanceASubOne a = InheritanceASubOne.queries.findByName("namea");
    Assert.assertEquals("a", a.getOne());

    InheritanceASubOne otherA = InheritanceASubOne.queries.find(1);
    Assert.assertTrue(otherA == a);
  }

  public void testDelete() {
    InheritanceASubOne a = new InheritanceASubOne();
    a.setName("name");
    a.setOne("one");
    this.commitAndReOpen();

    InheritanceASubOne.queries.delete(a);
    this.commitAndReOpen();
  }

}
