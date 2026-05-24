package geordmod.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class GeordConfig {
    public static boolean crystalOptimizer = true;
    public static float smoothHotbarSpeed = 0.012f;
    public static float zoomSpeed = 0.005f;
    public static float zoomSmoothness = 0.5f;
    public static float rightHandHeight = 0.0f;
    public static float leftHandHeight = 0.0f;
    public static boolean noFire = false;
    public static boolean smallTotem = false;
    public static boolean smartHitbox = false;
    
    public static boolean disabledShieldTint = true; 

    private static final File CONFIG_FILE = new File("config/geordconfig.properties");

    public static double getHotbarPercent() { return (smoothHotbarSpeed - 0.001f) / 0.099f; }
    public static void setHotbarPercent(double percent) { smoothHotbarSpeed = (float) (0.001f + (percent * 0.099f)); }

    public static double getZoomSpeedPercent() { return (zoomSpeed - 0.001f) / 0.049f; }
    public static void setZoomSpeedPercent(double percent) { zoomSpeed = (float) (0.001f + (percent * 0.049f)); }

    public static double getZoomSmoothnessPercent() { return zoomSmoothness / 0.99f; }
    public static void setZoomSmoothnessPercent(double percent) { zoomSmoothness = (float) (percent * 0.99f); }

    public static double getRightHandPercent() { return (rightHandHeight + 1.0f) / 2.0f; }
    public static void setRightHandPercent(double percent) { rightHandHeight = (float) (-1.0f + (percent * 2.0f)); }

    public static double getLeftHandPercent() { return (leftHandHeight + 1.0f) / 2.0f; }
    public static void setLeftHandPercent(double percent) { leftHandHeight = (float) (-1.0f + (percent * 2.0f)); }

    public static void load() {
        try {
            if (!CONFIG_FILE.exists()) {
                CONFIG_FILE.getParentFile().mkdirs();
                save(); 
                return;
            }
            
            Properties props = new Properties();
            try (FileInputStream in = new FileInputStream(CONFIG_FILE)) {
                props.load(in);
            }
            
            crystalOptimizer = Boolean.parseBoolean(props.getProperty("crystalOptimizer", "true"));
            smoothHotbarSpeed = Float.parseFloat(props.getProperty("smoothHotbarSpeed", "0.012"));
            zoomSpeed = Float.parseFloat(props.getProperty("zoomSpeed", "0.005"));
            zoomSmoothness = Float.parseFloat(props.getProperty("zoomSmoothness", "0.5"));
            rightHandHeight = Float.parseFloat(props.getProperty("rightHandHeight", "0.0"));
            leftHandHeight = Float.parseFloat(props.getProperty("leftHandHeight", "0.0"));
            noFire = Boolean.parseBoolean(props.getProperty("noFire", "false"));
            smallTotem = Boolean.parseBoolean(props.getProperty("smallTotem", "false"));
            smartHitbox = Boolean.parseBoolean(props.getProperty("smartHitbox", "false"));
            
            disabledShieldTint = Boolean.parseBoolean(props.getProperty("disabledShieldTint", "true"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            Properties props = new Properties();
            props.setProperty("crystalOptimizer", String.valueOf(crystalOptimizer));
            props.setProperty("smoothHotbarSpeed", String.valueOf(smoothHotbarSpeed));
            props.setProperty("zoomSpeed", String.valueOf(zoomSpeed));
            props.setProperty("zoomSmoothness", String.valueOf(zoomSmoothness));
            props.setProperty("rightHandHeight", String.valueOf(rightHandHeight));
            props.setProperty("leftHandHeight", String.valueOf(leftHandHeight));
            props.setProperty("noFire", String.valueOf(noFire));
            props.setProperty("smallTotem", String.valueOf(smallTotem));
            props.setProperty("smartHitbox", String.valueOf(smartHitbox));
            
            props.setProperty("disabledShieldTint", String.valueOf(disabledShieldTint));

            try (FileOutputStream out = new FileOutputStream(CONFIG_FILE)) {
                props.store(out, "Geord Client Configuration");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}