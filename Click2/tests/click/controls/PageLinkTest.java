package click.controls;

import junit.framework.Assert;

import org.exigencecorp.conversion.AbstractConverter;
import org.exigencecorp.conversion.Converter;

import click.AbstractPage;
import click.pages.AddModelPublicFieldPage;
import click.pages.HelloWorldPage;

public class PageLinkTest extends AbstractClickControlTest {

    public void testNoParameters() {
        PageLink p = new PageLink(HelloWorldPage.class);
        Assert.assertEquals("/helloWorld.htm", p.getHref());
    }

    public void testOneStringParameter() {
        PageLink p = new PageLink(AddModelPublicFieldPage.class);
        p.addParameter("bar");
        Assert.assertEquals("/addModelPublicField.htm?value=bar", p.getHref());
    }

    public void testTwoStringParametersMeansWeJustPickTheFirst() {
        PageLink p = new PageLink(TwoStringFieldsPage.class);
        p.addParameter("bar");
        Assert.assertEquals("click/controls/pageLinkTest$TwoStringFields.htm?value1=bar", p.getHref());
    }

    public void testTwoStringParametersGetTheSecondWithTheExplicitName() {
        PageLink p = new PageLink(TwoStringFieldsPage.class);
        p.addParameter("bar");
        p.addParameter("value2", "baz");
        Assert.assertEquals("click/controls/pageLinkTest$TwoStringFields.htm?value1=bar&value2=baz", p.getHref());
    }

    public void testParameterGetsConvertedToAString() {
        this.config.getUrlConverterRegistry().addConverter(PageLinkTest.employeeToString);

        Employee e = new Employee();
        e.id = 2;

        PageLink p = new PageLink(EmployeePage.class);
        p.addParameter(e);
        Assert.assertEquals("click/controls/pageLinkTest$Employee.htm?employee=2", p.getHref());
    }

    public static class TwoStringFieldsPage extends AbstractPage {
        public String value1;
        public String value2;
    }

    public static class EmployeePage extends AbstractPage {
        public Employee employee;
    }

    public static class Employee {
        public Integer id;
    }

    public static Converter<Employee, String> employeeToString = new AbstractConverter<Employee, String>() {
        public String convertOneToTwo(Employee value, Class<? extends String> toType) {
            return value.id.toString();
        }

        public Employee convertTwoToOne(String value, Class<? extends Employee> toType) {
            Employee e = new Employee();
            e.id = new Integer(value);
            return e;
        }
    };
}