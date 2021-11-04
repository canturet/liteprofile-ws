package com.liteprofile.ws.service;

import com.google.zxing.WriterException;

import java.io.IOException;

public interface QRCodeGeneratorService {

    void generateQRCode(String username) throws WriterException, IOException;

}
