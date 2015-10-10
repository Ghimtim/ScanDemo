package com.ghimtim.scannerdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class BookInfoDetailActivity extends Activity{

	private Intent intent;
    private TextView title,author,publisher,date,isbn,summary;
    private ImageView cover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_book_detail);

        title=(TextView)findViewById(R.id.bookview_title);
        author=(TextView)findViewById(R.id.bookview_author);
        publisher=(TextView)findViewById(R.id.bookview_publisher);
        date=(TextView)findViewById(R.id.bookview_publishdate);
        isbn=(TextView)findViewById(R.id.bookview_isbn);
        summary=(TextView)findViewById(R.id.bookview_summary);
        cover=(ImageView)findViewById(R.id.bookview_cover);

        intent=getIntent();
        //BookInfo book=(BookInfo) getIntent().getSerializableExtra(BookInfo.class.getName());
        BookInfo book=(BookInfo)intent.getParcelableExtra(BookInfo.class.getName());

        title.setText(book.getmTitle());
        author.setText(book.getmAuthor());
        publisher.setText(book.getmPublisher());
        date.setText(book.getmPublishDate());
        isbn.setText(book.getmISBN());
        summary.setText(book.getmSummary());
        cover.setImageBitmap(book.getmCover());
    }
}
