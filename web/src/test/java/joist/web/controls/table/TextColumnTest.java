package joist.web.controls.table;

import joist.web.controls.AbstractClickControlTest;
import joist.web.fakedomain.Employee;
import junit.framework.Assert;
import bindgen.joist.web.fakedomain.EmployeeBinding;

public class TextColumnTest extends AbstractClickControlTest {

    public void testNoParameters() {
        EmployeeBinding eb = new EmployeeBinding(new Employee(2));
        TextColumn p = new TextColumn(eb);
        Assert.assertEquals("Bob2", this.render(p));
    }

}
