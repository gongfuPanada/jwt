package eu.webtoolkit.jwt.examples.treeviewdragdrop;

import eu.webtoolkit.jwt.WObject;
import eu.webtoolkit.jwt.WStandardItemModel;
import eu.webtoolkit.jwt.WString;

/**
 * A specialized standard item model which report a specific drag and drop mime
 * type.
 * 
 * A specific drag and drop mime type instead of the generic abstract item model
 * is returned by the model.
 */
public class FileModel extends WStandardItemModel {
	/**
	 * Constructor.
	 */
	public FileModel(WObject parent) {
		super(parent);
	}

	/**
	 * Return the mime type.
	 */
	public String mimeType() {
		return FolderView.FileSelectionMimeType;
	}

	/**
	 * Date display format.
	 */
	public static String dateDisplayFormat = "MMM dd, yyyy";

	/**
	 * Date edit format.
	 */
	public static String dateEditFormat = "dd-MM-yyyy";
}
