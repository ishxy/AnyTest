package androidtest.activity;

import androidtest.activity.Activity;
import androidtest.activity.ActivityBean;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ActivityPaser extends DefaultHandler {

    private List<ActivityBean> resList = new ArrayList<>();
    private ActivityBean activityBean = null;

    public List<ActivityBean> getResList() {
        return resList;
    }


    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName.equals("activity")) {
            activityBean = new ActivityBean();
            activityBean.setName(attributes.getValue("name"));
        }else if (qName.equals("action")){
            activityBean.setAction(attributes.getValue("name"));
        }
    }
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (qName.equals("activity")){
            resList.add(activityBean);
        }
    }
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
    }



    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
