package androidtest.activity;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ParserTest {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

    }

    private List<ActivityBean> resList = null;

    public void paserManifest() {
        try {
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();
            ActivityPaser handler = new ActivityPaser();
            String path = new File("").getAbsolutePath() + File.separatorChar;
            parser.parse(new File("Manifest.xml"), handler);
            resList = handler.getResList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ActivityBean getlauncher() {
        ActivityBean bean = null;
        for (ActivityBean b : resList) {
            if (b.getAction().equals("MAIN")) {
                return b;
            }
        }
        return bean;
    }
}
