package uz.spring.oson_shop_uz.admin.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.spring.oson_shop_uz.admin.entity.Attachment;
import uz.spring.oson_shop_uz.admin.entity.Product;
import uz.spring.oson_shop_uz.admin.entity.SubCategory;
import uz.spring.oson_shop_uz.admin.receive.ProductDTO;
import uz.spring.oson_shop_uz.admin.repository.AttachmentRepository;
import uz.spring.oson_shop_uz.admin.repository.ProductRepository;
import uz.spring.oson_shop_uz.admin.repository.SubCategoryRepository;
import uz.spring.oson_shop_uz.admin.response.ApiResponse;
import uz.spring.oson_shop_uz.admin.security.base.BaseService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class ProductService implements BaseService<ProductDTO, ApiResponse, Long> {
    private static final String uploadDirectory = "yuklanganlar";
    private final ProductRepository productRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final AttachmentRepository attachmentRepository;
    private final AttachmentService attachmentService;
    @Autowired
    public ProductService(ProductRepository productRepository, SubCategoryRepository subCategoryRepository, AttachmentRepository attachmentRepository, AttachmentService attachmentService) {
        this.productRepository = productRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.attachmentRepository = attachmentRepository;
        this.attachmentService = attachmentService;
    }

    public ApiResponse add(ProductDTO productDTO,MultipartFile file) throws IOException {
        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(productDTO.getSubCategoryId());
        MultipartFile systemImg = attachmentService.uploadFileToSystemImg(file);
        if (optionalSubCategory.isPresent() && systemImg != null) {
            SubCategory subCategory = optionalSubCategory.get();
            Product product = new Product();
            Attachment attachment = new Attachment();
            attachment.setName(file.getName());
            attachment.setSize(file.getSize());
            attachment.setFileOriginalName(file.getOriginalFilename());
            attachment.setContentType(file.getContentType());
            product.setName(productDTO.getProductName());
            product.setPrice(productDTO.getPrice());
            product.setSubCategory(subCategory);
            product.setAttachments(attachment);
            productRepository.save(product);
            return new ApiResponse("successfully added product", true);
        }
        return new ApiResponse("this sub category is not DB", false);

    }

    @Override
    public ApiResponse add(ProductDTO productDTO) {
        return null;
    }

    @Override
    public List<Long> get() {
        return null;
    }

    @Override

    public ApiResponse edit(ProductDTO product, Long id) {
        return null;
    }

    @Override
    public ApiResponse delete(Long id) {
        return null;
    }

    public ApiResponse addProduct(ProductDTO productDTO, MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            Attachment attachment = new Attachment();
            attachment.setFileOriginalName(originalFilename);
            attachment.setSize(file.getSize());
            attachment.setContentType(file.getContentType());
            String[] split = originalFilename.split("\\.");
            String name = UUID.randomUUID().toString() + "." + split[split.length - 1];
            attachment.setName(name);
            Attachment savedAttachment = attachmentRepository.save(attachment);
            Path path = Paths.get(uploadDirectory + "/" + name);
            Files.copy(file.getInputStream(), path);

        }
        return new ApiResponse("ERROR", false);
    }
}
