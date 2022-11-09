package uz.spring.oson_shop_uz.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.spring.oson_shop_uz.admin.repository.ProductRepository;
import uz.spring.oson_shop_uz.entity.Payment;
import uz.spring.oson_shop_uz.entity.Person;
import uz.spring.oson_shop_uz.entity.Product;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PDFGeneratorService {
    private final PaymentService paymentService;
    private final ProductRepository productRepository;

    public void export(HttpServletResponse response) throws IOException {

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Person currentUser = (Person) authentication.getPrincipal();
            if (!currentUser.equals("anonymousUser")) {
                Document document = new Document(PageSize.A4);
                PdfWriter.getInstance(document, response.getOutputStream());
                document.open();

                Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                fontTitle.setSize(18);

                Paragraph paragraph = new Paragraph("Product about buy ", fontTitle);
                paragraph.setAlignment(Paragraph.ALIGN_CENTER);

                Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
                fontParagraph.setSize(12);

                for (Payment payment : paymentService.list()) {
                    Long productId = payment.getProductId();
                    Optional<Product> optionalProduct = productRepository.findById(productId);
                    if (optionalProduct.isPresent()) {
                        Product product = optionalProduct.get();
                        Paragraph paragraph2 = new Paragraph("product name " + product.getName(), fontParagraph);
                        paragraph2.setAlignment(Paragraph.ALIGN_CENTER);

                        Paragraph paragraph3 = new Paragraph("product check id " + payment.getCheckId(), fontParagraph);
                        paragraph3.setAlignment(Paragraph.ALIGN_CENTER);

                        Paragraph paragraph4 = new Paragraph("product summa " + payment.getAmount(), fontParagraph);
                        paragraph4.setAlignment(Paragraph.ALIGN_CENTER);

                        document.add(paragraph2);
                        document.add(paragraph3);
                        document.add(paragraph4);

                    }

                }
                document.close();
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }
}
