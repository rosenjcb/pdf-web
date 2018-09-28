package technology.tabula.tabula_web.background.job;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import technology.tabula.tabula_web.extractor.PagesInfoExtractor;
import technology.tabula.tabula_web.workspace.DocumentPage;
import technology.tabula.tabula_web.workspace.WorkspaceDAO;
import technology.tabula.tabula_web.workspace.WorkspaceDocument;

public class GenerateTemplateDocumentData extends Job {

	String filePath; 
	String originalFilename; 
	String id; 
	int[] thumbnailSizes;

	private WorkspaceDAO workspaceDAO;
	
	public GenerateTemplateDocumentData(String filePath, String originalFilename, String id,
			int[] thumbnailSizes, UUID batch, WorkspaceDAO workspaceDAO) {
		
		super(batch);
		this.filePath = filePath;
		this.originalFilename = originalFilename;
		this.id = id;
		this.thumbnailSizes = thumbnailSizes.clone();

		this.workspaceDAO = workspaceDAO;
	}

	@Override
	public void perform() throws Exception {
	    at(1, 100, "opening workspace...");

	    at(100, 100, "Complete");
	}

}
