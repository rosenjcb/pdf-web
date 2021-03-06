package technology.tabula.tabula_web;
import static spark.Spark.*;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import spark.route.RouteOverview;
import technology.tabula.tabula_web.routes.IndexRouteGroup;
import technology.tabula.tabula_web.routes.JobProgressRouteGroup;
import technology.tabula.tabula_web.routes.PdfRouteGroup;
import technology.tabula.tabula_web.routes.PdfsRouteGroup;
import technology.tabula.tabula_web.workspace.WorkspaceDAO;
import technology.tabula.tabula_web.workspace.FileWorkspaceDAO;
import technology.tabula.tabula_web.workspace.WorkspaceException;


public class App {

	private static final String VERSION = "1.1.0";
	
	public static void main(String[] args) throws JsonIOException, JsonSyntaxException, IOException, WorkspaceException {
		staticFiles.location("/public");
		RouteOverview.enableRouteOverview();
		
		WorkspaceDAO workspaceDAO = new FileWorkspaceDAO(Settings.getDataDir());
				
	    path("/", new IndexRouteGroup(workspaceDAO));
	    path("/pdfs/", new PdfsRouteGroup(workspaceDAO));
	    path("/pdf/", new PdfRouteGroup(workspaceDAO));
	    path("/queue/", new JobProgressRouteGroup());

	    get("/version", (req, rsp) -> {
			rsp.type("application/json");
			return String.format("{ \"api\": \"%s\" }", VERSION);
		});

	    get("/templates", (req, rsp) -> {
			rsp.type("application/json");
			return workspaceDAO.getTemplates();
		});
	    
	    get("/templates/:template_id", (req, rsp) -> {
	    	String templateId = req.params(":template_id");
	    	System.out.println("templateId=" + templateId.substring(1, templateId.length() -1));
			return workspaceDAO.getTemplate(templateId.substring(1, templateId.length() -1));
		});

	}
}

