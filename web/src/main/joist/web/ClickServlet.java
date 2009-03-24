package joist.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import joist.web.exceptions.IoException;

public abstract class ClickServlet extends HttpServlet {

    private static final long serialVersionUID = 1;
    private ServletConfig servletConfig;
    private ClickConfig clickConfig;

    public ClickServlet() {
    }

    @Override
    public void init(ServletConfig servletConfig) {
        this.servletConfig = servletConfig;
        this.clickConfig = this.createClickConfig();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ClickContext context = new ClickContext(this.servletConfig, this.clickConfig, request, response);
        CurrentContext.set(context);

        try {
            Page page = this.getPage(context);
            context.setPage(page);
            page.getProcessor().process(page);
        } catch (IoException io) {
            throw io.getCause();
        }
    }

    protected Page getPage(ClickContext context) {
        // Get the path, e.g. /page.htm (without the webapp context)
        String path = context.getRequest().getServletPath();
        String className = context.getClickConfig().getPageResolver().getPageFromPath(path);
        try {
            return (Page) Class.forName(className).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** Should be implemented by each app to create its configuration. */
    protected abstract ClickConfig createClickConfig();

}