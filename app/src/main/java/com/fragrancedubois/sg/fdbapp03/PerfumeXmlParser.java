package com.fragrancedubois.sg.fdbapp03;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by philip on 9/30/14.
 */
public class PerfumeXmlParser {

    private static final String ns = null;

    // We don't use namespaces

    public List<Entry> parse(InputStream in) throws XmlPullParserException, IOException {
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

    private List<Entry> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<Entry> entries = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, ns, "fdb");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("perfume")) {
                entries.add(readEntry(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    // This class represents a single entry (post) in the XML feed.
    // It includes the data members "title," "link," and "summary."
    public static class Entry {
        public static float mScreenRatioW;
        public static float mScreenRatioH;

        public final String title;
        public final String perfumer;
        public final String topnotes;
        public final String middlenotes;
        public final String basenodes;
        public final String thumbnail;
        public final String themecolor;
        public final String fontcolor;
        public final String portrait;
        public final String profile;
        public final String cwx;
        public final String cwy;
        public final float mXcoord;
        public final float mYcoord;
        final float mLX = 800f;
        final float mLY = 150f;
        float mRealX = 0;
        float mRealY = 0;

        public int iCww;
        public int iCwh;

        View mFlower = null;
        View mView;

        private Entry(String title, String perfumer, String topnotes, String middlenotes,
                      String basenodes, String thumbnail, String themecolor, String fontcolor,
                      String portrait, String profile, String cwx, String cwy, String cww, String cwh) {
            this.title = title;
            this.perfumer = perfumer;
            this.topnotes = topnotes;
            this.middlenotes = middlenotes;
            this.basenodes = basenodes;
            this.thumbnail = thumbnail;
            this.themecolor = themecolor;
            this.fontcolor = fontcolor;
            this.portrait = portrait;
            this.profile = profile;
            this.cwx = cwx;
            this.cwy = cwy;
            this.mXcoord = Float.parseFloat(cwx);
            this.mYcoord = Float.parseFloat(cwy);
            //this.mRealY = FdbHelper.fdbHelperCalcYCoord(mYcoord);
            //this.mRealX = FdbHelper.fdbHelperCalcXCoord(mXcoord);

            iCww = Integer.parseInt(cww);
            iCwh = Integer.parseInt(cwh);
        }

        public Entry(Entry parentEntry) {
            this.title = parentEntry.title;
            this.perfumer = parentEntry.perfumer;
            this.topnotes = parentEntry.topnotes;
            this.middlenotes = parentEntry.middlenotes;
            this.basenodes = parentEntry.basenodes;
            this.thumbnail = parentEntry.thumbnail;
            this.themecolor = parentEntry.themecolor;
            this.fontcolor = parentEntry.fontcolor;
            this.portrait = parentEntry.portrait;
            this.profile = parentEntry.profile;
            this.cwx = parentEntry.cwx;
            this.cwy = parentEntry.cwy;
            this.mXcoord = Float.parseFloat(parentEntry.cwx);
            this.mYcoord = Float.parseFloat(parentEntry.cwy);

            this.mRealY = FdbHelper.fdbHelperCalcYCoord(mYcoord);
            this.mRealX = FdbHelper.fdbHelperCalcXCoord(mXcoord);

        }

        /*
        public View drawFlower(final Activity activity) {
            ImageView flower = new ImageView(activity);
            // make flower invisible
            //flower.setImageResource(R.drawable.icon);
            //flower.setVisibility(View.INVISIBLE);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(80, 80);
            flower.setLayoutParams(layoutParams);

            flower.setX(mRealX);
            flower.setY(mRealY);

            final Entry perfume = this;
            flower.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.e("fdb:PerfumeXmlParser", "flower.setOnClickListener");
                    Intent myIntent;
                    myIntent = new Intent(v.getContext(), ScreenSlideActivity.class);
                    ArrayList<String> strList = new ArrayList();

                    strList.add(0, perfume.title);
                    strList.add(1, perfume.thumbnail);
                    strList.add(2, perfume.themecolor);
                    strList.add(3, perfume.topnotes);
                    strList.add(4, perfume.middlenotes);
                    strList.add(5, perfume.basenodes);
                    strList.add(6, perfume.perfumer);
                    strList.add(7, perfume.fontcolor);
                    strList.add(8, perfume.portrait);
                    strList.add(9, perfume.profile);

                    myIntent.putExtra("perfumedata", strList);
                    activity.startActivityForResult(myIntent, 0);
                }
            });

            return flower;
        }
        */

        public View drawPerfume0(final Activity activity, final List<FdbAddition> notes) {

            // convert List to ArrayList
            final ArrayList<FdbAddition> notesArrayList = new ArrayList<>();
            for (FdbAddition note : notes) {
                notesArrayList.add(note);
            }

            for (FdbAddition noteObj : notesArrayList) {
                //Log.e("drawPerfume, noteObj.mName=", "|" + noteObj.mName + "|");
            }

            int flowerWidth = 110;
            ImageView flower = new ImageView(activity);
            // make flower invisible
            //flower.setImageResource(R.drawable.icon);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(flowerWidth, flowerWidth);
            flower.setLayoutParams(layoutParams);

            flower.setX(mRealX);
            flower.setY(mRealY);

            mFlower = flower;

            final Entry perfume = this;
            flower.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.e("fdb:PerfumeXmlParser", "flower.setOnClickListener");
                    Intent myIntent;
                    myIntent = new Intent(v.getContext(), ScreenSlideActivity.class);
                    ArrayList<String> strList = new ArrayList();

                    strList.add(0, perfume.title);
                    strList.add(1, perfume.thumbnail);
                    strList.add(2, perfume.themecolor);
                    strList.add(3, perfume.topnotes);
                    strList.add(4, perfume.middlenotes);
                    strList.add(5, perfume.basenodes);
                    strList.add(6, perfume.perfumer);
                    strList.add(7, perfume.fontcolor);
                    strList.add(8, perfume.portrait);
                    strList.add(9, perfume.profile);

                    myIntent.putExtra("perfumedata", strList);
                    myIntent.putExtra("notesdata", notesArrayList);
                    activity.startActivityForResult(myIntent, 0);
                }
            });

            return flower;
        }

        public View drawPerfume(final Activity activity, final List<FdbAddition> notes, boolean mFlowerFlag) {
            if (mFlowerFlag) {
                int flowerWidth = 30;
                ImageView flower = new ImageView(activity);
                flower.setImageResource(R.drawable.fdb_flower);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(flowerWidth, flowerWidth);
                flower.setLayoutParams(layoutParams);
                //flower.setX(mRealX);
                //flower.setY(mRealY);
                mView = flower;
            } else {
                this.mRealY = FdbHelper.fdbHelperCalcYCoord(mYcoord);
                this.mRealX = FdbHelper.fdbHelperCalcXCoord(mXcoord);

                /*
                TextView tv = new TextView(activity);
                tv.setText(this.title);
                tv.setTextColor(Color.WHITE);
                tv.measure(0, 0);       //must call measure!
                int mh = tv.getMeasuredHeight(); //get height
                int mw = tv.getMeasuredWidth();

                int finalX = (int) (mRealX - mw / 2);
                int finalY = (int) (mRealY);

                tv.setX(finalX);
                tv.setY(finalY);

                mView = tv;
                */

                Button btn = new Button(activity);
                btn.setVisibility(View.VISIBLE);
                btn.setBackgroundColor(Color.TRANSPARENT);

                int finalX = (int) (mRealX);
                int finalY = (int) (mRealY);
                btn.setX(finalX);
                btn.setY(finalY);
                mView = btn;

                // http://stackoverflow.com/questions/6040218/how-can-we-change-the-button-size-in-java-code
                int width = FdbHelper.fdbHelperCalcLength(iCww);
                int height = FdbHelper.fdbHelperCalcLength(iCwh);

                btn.setLayoutParams(new LinearLayout.LayoutParams(
                        FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));
                //btn.setWidth(width);
                btn.setHeight(height);
                btn.getLayoutParams().width= width;

                Log.e("perfume title", title);
                Log.e("perfume mXcoord", "" + mXcoord);
                Log.e("perfume mYcoord", "" + mYcoord);

                Log.e("perfume mRealX", "" + mRealX);
                Log.e("perfume mRealY", "" + mRealY);

                Log.e("perfume finalX", "" + finalX);
                Log.e("perfume finalY", "" + finalY);

                Log.e("perfume width", "" + width);
                Log.e("perfume height", "" + height);
            }

            // convert List to ArrayList
            final ArrayList<FdbAddition> notesArrayList = new ArrayList<>();
            for (FdbAddition note : notes) {
                notesArrayList.add(note);
            }

            for (FdbAddition noteObj : notesArrayList) {
                //Log.e("drawPerfume, noteObj.mName=", "|" + noteObj.mName + "|");
            }

            final Entry perfume = this;
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.e("fdb:PerfumeXmlParser", "flower.setOnClickListener");
                    Intent myIntent;
                    myIntent = new Intent(v.getContext(), ScreenSlideActivity.class);
                    ArrayList<String> strList = new ArrayList();

                    strList.add(0, perfume.title);
                    strList.add(1, perfume.thumbnail);
                    strList.add(2, perfume.themecolor);
                    strList.add(3, perfume.topnotes);
                    strList.add(4, perfume.middlenotes);
                    strList.add(5, perfume.basenodes);
                    strList.add(6, perfume.perfumer);
                    strList.add(7, perfume.fontcolor);
                    strList.add(8, perfume.portrait);
                    strList.add(9, perfume.profile);

                    myIntent.putExtra("perfumedata", strList);
                    myIntent.putExtra("notesdata", notesArrayList);
                    activity.startActivityForResult(myIntent, 0);
                }
            });

            return mView;
        }


    }

    // Parses the contents of an entry. If it encounters a title, summary, or link tag, hands them
    // off
    // to their respective &quot;read&quot; methods for processing. Otherwise, skips the tag.
    private Entry readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "perfume");
        String title = null;
        String perfumer = null;
        String topnotes = null;
        String middlenotes = null;
        String basenotes = null;
        String thumbnail = null;
        String themecolor = null;
        String fontcolor = null;
        String portrait = null;
        String profile = null;
        String cwx = null;
        String cwy = null;

        String cww = null;
        String cwh = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("title")) {
                title = readTitle(parser);
            } else if (name.equals("perfumer")) {
                perfumer = readByNoteName(parser, name);
            } else if (name.equals("topnotes")) {
                topnotes = readByNoteName(parser, name);
            } else if (name.equals("middlenotes")) {
                middlenotes = readByNoteName(parser, name);
            } else if (name.equals("basenotes")) {
                basenotes = readByNoteName(parser, name);
            } else if (name.equals("thumbnail")) {
                thumbnail = readByNoteName(parser, name);
            } else if (name.equals("themecolor")) {
                themecolor = readByNoteName(parser, name);
            } else if (name.equals("fontcolor")) {
                fontcolor = readByNoteName(parser, name);
            } else if (name.equals("portrait")) {
                portrait = readByNoteName(parser, name);
            } else if (name.equals("profile")) {
                profile = readByNoteName(parser, name);
            } else if (name.equals("cwx")) {
                cwx = readByNoteName(parser, name);
            } else if (name.equals("cwy")) {
                cwy = readByNoteName(parser, name);
            } else if (name.equals("cww")) {
                cww = readByNoteName(parser, name);
            } else if (name.equals("cwh")) {
                cwh = readByNoteName(parser, name);
            }

            else {
                skip(parser);
            }
        }
        return new Entry(title, perfumer, topnotes, middlenotes, basenotes, thumbnail, themecolor, fontcolor, portrait, profile, cwx, cwy, cww, cwh);
    }

    // Processes title tags in the feed.
    private String readByNoteName(XmlPullParser parser, String node) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, node);
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, node);
        return title;
    }

    // Processes title tags in the feed.
    private String readTitle(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "title");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "title");
        return title;
    }

    // Processes link tags in the feed.
    private String readLink(XmlPullParser parser) throws IOException, XmlPullParserException {
        String link = "";
        parser.require(XmlPullParser.START_TAG, ns, "link");
        String tag = parser.getName();
        String relType = parser.getAttributeValue(null, "rel");
        if (tag.equals("link")) {
            if (relType.equals("alternate")) {
                link = parser.getAttributeValue(null, "href");
                parser.nextTag();
            }
        }
        parser.require(XmlPullParser.END_TAG, ns, "link");
        return link;
    }

    // Processes summary tags in the feed.
    private String readSummary(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "summary");
        String summary = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "summary");
        return summary;
    }

    // For the tags title and summary, extracts their text values.
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    // Skips tags the parser isn't interested in. Uses depth to handle nested tags. i.e.,
    // if the next tag after a START_TAG isn't a matching END_TAG, it keeps going until it
    // finds the matching END_TAG (as indicated by the value of "depth" being 0).
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
}

