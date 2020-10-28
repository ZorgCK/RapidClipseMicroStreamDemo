
package one.microstream.microstreamdemo.storage;

import one.microstream.storage.configuration.Configuration;
import one.microstream.storage.types.EmbeddedStorageManager;


public final class DB
{
	public static DataRoot               root = new DataRoot();
	public static EmbeddedStorageManager storageManager;

	static
	{
		DB.storageManager = Configuration.LoadXml(
			DB.class.getResource("StorageConfig.xml"))
			.createEmbeddedStorageFoundation()
			.createEmbeddedStorageManager(DB.root)
			.start();
	}
}
