package ru.clevertec.checkservlets.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.checkservlets.check.CheckGenerator;
import ru.clevertec.checkservlets.check.CheckUtil;
import ru.clevertec.checkservlets.check.builder.CheckPdfBuilder;
import ru.clevertec.checkservlets.model.Check;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/api/check")
public class CheckServlet extends HttpServlet {

    /**
     * Handles HTTP GET requests for getting check in pdf format.<br/>
     * Arguments should be in next format: <code>idOfProduct-quantity&...&card-idOfDiscountCard<code/>
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, String> allParams = new HashMap<>();
        for (Map.Entry<String, String[]> e : parameterMap.entrySet()) {
            for (String quantity : e.getValue()) {
                allParams.put(e.getKey(), quantity);
            }
        }
        try {
            String[] args = CheckUtil.parseParams(allParams);
            Map<Integer, Integer> info = CheckUtil.parseArguments(args);
            Check check = CheckGenerator.generateCheck(info);
            CheckPdfBuilder pdfBuilder = new CheckPdfBuilder();
            String filePath = pdfBuilder.buildCheck(check.getProducts(), check.getDiscountCard());
            byte[] content = Files.readAllBytes(Path.of(filePath));
            response.setContentType("application/pdf");
            response.setContentLength(content.length);
            try (OutputStream os = response.getOutputStream()) {
                os.write(content, 0, content.length);
            }
        } catch (Exception ex) {
            response.sendError(400, ex.getMessage());
        }
    }
}
