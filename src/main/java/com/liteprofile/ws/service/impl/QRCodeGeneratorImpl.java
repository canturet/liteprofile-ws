package com.liteprofile.ws.service.impl;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.liteprofile.ws.service.QRCodeGeneratorService;
import org.springframework.stereotype.Service;

@Service
public class QRCodeGeneratorImpl implements QRCodeGeneratorService {

    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/qr_codes/QRCode-";
    private static final String USER_PROFILE_PATH = "http://localhost:8081/api/user/";

    public void generateQRCode(String username)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(USER_PROFILE_PATH + username, BarcodeFormat.QR_CODE, 350, 350);
        String filePath = QR_CODE_IMAGE_PATH + username + ".png";
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
}
