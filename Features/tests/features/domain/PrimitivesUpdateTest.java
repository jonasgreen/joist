package features.domain;

import java.util.List;

import junit.framework.Assert;

import org.exigencecorp.util.Copy;

public class PrimitivesUpdateTest extends AbstractFeaturesTest {

    public void testChangeFlag() {
        new Primitives("testSave");
        this.commitAndReOpen();

        Assert.assertEquals(false, Primitives.queries.find(2).getFlag());
        List<Integer> ids = Copy.list(2);
        Primitives.queries.setFlag(ids, true);
        this.commitAndReOpen();

        Assert.assertEquals(true, Primitives.queries.find(2).getFlag());
    }

    public void testChangeFlagWithDynamicList() {
        new Primitives("foo1");
        new Primitives("foo2");
        new Primitives("bar");
        this.commitAndReOpen();

        List<Integer> ids = Primitives.queries.findIdsWithNameLike("foo%");
        Assert.assertEquals(2, ids.size());
        Assert.assertEquals(false, Primitives.queries.find(2).getFlag());
        Assert.assertEquals(false, Primitives.queries.find(3).getFlag());
        Assert.assertEquals(false, Primitives.queries.find(4).getFlag());

        Primitives.queries.setFlag(ids, true);
        this.commitAndReOpen();

        Assert.assertEquals(true, Primitives.queries.find(2).getFlag());
        Assert.assertEquals(true, Primitives.queries.find(3).getFlag());
        Assert.assertEquals(false, Primitives.queries.find(4).getFlag());
    }

}
