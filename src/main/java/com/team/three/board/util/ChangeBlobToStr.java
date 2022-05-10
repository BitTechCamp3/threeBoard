package com.team.three.board.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.SQLException;

public class ChangeBlobToStr {
    private final String change(Blob blob) throws Exception {
        String s = "";
        InputStream inStream = blob.getBinaryStream();
        InputStreamReader inStreamReader = new InputStreamReader(inStream);
        BufferedReader reader = new BufferedReader(inStreamReader);
        StringBuffer buf = new StringBuffer();
        while (s == reader.readLine()) {
            buf.append(s);
        }
        return s;
    }
}
