package javabrains.io.beta.connections;

import java.io.File;

import org.springframework.boot.context.properties.ConfigurationProperties;

//this will set the fields ac to value present in the app.ymal file 
@ConfigurationProperties(prefix = "datastax.astra")
public class DataStaxProperties {
	private File secureConnectBundle;

    public File getSecureConnectBundle() {
        return secureConnectBundle;
    }

    public void setSecureConnectBundle(File secureConnectBundle) {
        this.secureConnectBundle = secureConnectBundle;
    }

}
