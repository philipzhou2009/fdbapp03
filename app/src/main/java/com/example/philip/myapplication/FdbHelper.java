package com.example.philip.myapplication;

/**
 * Created by philip on 10/13/14.
 */

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class FdbHelper {
    private static final String ns = null;
    public static float mDiameterRatio = 1.0f;
    public static float mScreenWidth = 2560.0f;
    public static float mScreenHeight = 1600.0f;


    public static float mBgMarginTop = 0;
    public static int mMarginTop = 0;
    public static int mMarginLeft = 0;

    public static int getId(String resourceName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resourceName);
            return idField.getInt(idField);
        } catch (Exception e) {
            throw new RuntimeException("No resource ID found for: "
                    + resourceName + " / " + c, e);
        }
    }

    public static class Personality {
        public String mName;
        public float mXcoord;
        public float mYcoord;
        public float mDegree;

        private Personality(String name, float xcoord, float ycoord, float degree) {
            this.mName = name;
            this.mXcoord = xcoord;
            this.mYcoord = ycoord;
            this.mDegree = degree;
        }
    }

    static public List<FdbWheeler> loadPersonalityXml(Activity activity) throws XmlPullParserException, IOException {
        InputStream stream = null;
        FdbHelper xmlParser = new FdbHelper();
        List<FdbWheeler> list = null;

        AssetManager assetMgr = activity.getAssets();

        try {
            stream = assetMgr.open("personalities.xml");
            list = xmlParser.parseWheeler(stream);
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (stream != null) {
                stream.close();
            }
        }

        return list;
    }

    static public List<PerfumeXmlParser.Entry> loadPerfumeXml(Activity activity) throws XmlPullParserException, IOException {
        InputStream stream = null;
        PerfumeXmlParser perfumeXmlParser = new PerfumeXmlParser();
        List<PerfumeXmlParser.Entry> entries = null;
        String title = null;
        String url = null;
        String summary = null;

        AssetManager assetMgr = activity.getAssets();

        try {
            stream = assetMgr.open("perfumes.xml");
            entries = perfumeXmlParser.parse(stream);
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (stream != null) {
                stream.close();
            }
        }

        return entries;
    }

    public List<Personality> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private List<Personality> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<Personality> entries = new ArrayList<Personality>();

        parser.require(XmlPullParser.START_TAG, ns, "personalities");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("personality")) {
                entries.add(readEntry(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    private Personality readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "personality");
        String title = null;
        float xcoord = 0;
        float ycoord = 0;
        float degree = 0f;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("name")) {
                title = readByNoteName(parser, name);
            } else if (name.equals("x")) {
                xcoord = Float.parseFloat(readByNoteName(parser, name));
            } else if (name.equals("y")) {
                ycoord = Float.parseFloat(readByNoteName(parser, name));
            } else if (name.equals("degree")) {
                degree = Float.parseFloat(readByNoteName(parser, name));
            } else {
                skip(parser);
            }
        }
        return new Personality(title, xcoord, ycoord, degree);
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

    private String readByNoteName(XmlPullParser parser, String node) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, node);
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, node);
        return title;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    public List<FdbWheeler> parseWheeler(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readWheeler(parser);
        } finally {
            in.close();
        }
    }

    private List<FdbWheeler> readWheeler(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<FdbWheeler> entries = new ArrayList<FdbWheeler>();

        parser.require(XmlPullParser.START_TAG, ns, "personalities");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("personality")) {
                entries.add(readPersonality(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    private FdbWheeler readPersonality(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "personality");
        String title = null;
        float xcoord = 0;
        float ycoord = 0;
        float degree = 0f;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("name")) {
                title = readByNoteName(parser, name);
            } else if (name.equals("x")) {
                xcoord = Float.parseFloat(readByNoteName(parser, name));
            } else if (name.equals("y")) {
                ycoord = Float.parseFloat(readByNoteName(parser, name));
            } else if (name.equals("degree")) {
                degree = Float.parseFloat(readByNoteName(parser, name));
            } else {
                skip(parser);
            }
        }
        return new FdbWheeler(title, xcoord, ycoord, degree);
    }

    static public List<FdbAddition> loadAdditionsXml(Activity activity) throws XmlPullParserException, IOException {
        InputStream stream = null;
        FdbHelper xmlParser = new FdbHelper();
        List<FdbAddition> Additions = null;
        String title = null;
        String url = null;
        String summary = null;

        AssetManager assetMgr = activity.getAssets();

        try {
            stream = assetMgr.open("additions.xml");
            Additions = xmlParser.parseAdditions(stream);
        } finally {
            if (stream != null) {
                stream.close();
            }
        }

        return Additions;
    }

    public List<FdbAddition> parseAdditions(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readAdditions(parser);
        } finally {
            in.close();
        }
    }

    private List<FdbAddition> readAdditions(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<FdbAddition> entries = new ArrayList<FdbAddition>();

        parser.require(XmlPullParser.START_TAG, ns, "additions");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("addition")) {
                entries.add(readAddition(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    private FdbAddition readAddition(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "addition");
        String title = null;
        float xcoord = 0;
        float ycoord = 0;
        String imagename = null;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("name")) {
                title = readByNoteName(parser, name);
            } else if (name.equals("x")) {
                xcoord = Float.parseFloat(readByNoteName(parser, name));
            } else if (name.equals("y")) {
                ycoord = Float.parseFloat(readByNoteName(parser, name));
            } else if (name.equals("img")) {
                imagename = readByNoteName(parser, name);
            } else {
                skip(parser);
            }
        }
        return new FdbAddition(title, xcoord, ycoord, imagename);
    }

    public static float fdbHelperCalcYCoord0(float ycoord) {
        float rycoord = 0f;
        rycoord = (ycoord - 800) * mDiameterRatio + mScreenHeight / 2.0f;
        return rycoord;
    }

    public static float fdbHelperCalcXCoord0(float ycoord, float xorg, float yorg) {

        double fTmp1 = mDiameterRatio * mDiameterRatio * ((xorg - 1280.0f) * (xorg - 1280.0f) + (yorg - 800.0f) * (yorg - 800.0f)) - (ycoord - mScreenHeight / 2.0f) * (ycoord - mScreenHeight / 2.0f);
        double fTmp2 = Math.sqrt(fTmp1);
        float dTmp3 = 0f;
        if (xorg > 1280.0f) {
            dTmp3 = (float) (mScreenWidth / 2.0 + fTmp2);
        } else {
            dTmp3 = (float) (mScreenWidth / 2.0 - fTmp2);
        }
        //Log.e("fcw, xxx=", Float.toString((ycoord - mScreenHeight / 2.0f) * (ycoord - mScreenHeight / 2.0f)));

        return dTmp3;
    }

    public static float fdbHelperCalcYCoord(float ycoord) {
        return ycoord * mDiameterRatio + getmMarginTop();
    }


    public static float fdbHelperCalcXCoord(float xcoord) {
        return xcoord * mDiameterRatio + getmMarginLeft();
    }

    public static void setmBgMarginTop(float mBgMarginTop) {
        FdbHelper.mBgMarginTop = mBgMarginTop;
    }

    public static int getmMarginTop() {
        return mMarginTop;
    }

    public static void setmMarginTop(int mMarginTop) {
        FdbHelper.mMarginTop = mMarginTop;
    }

    public static int getmMarginLeft() {
        return mMarginLeft;
    }

    public static void setmMarginLeft(int mMarginLeft) {
        FdbHelper.mMarginLeft = mMarginLeft;
    }
}

