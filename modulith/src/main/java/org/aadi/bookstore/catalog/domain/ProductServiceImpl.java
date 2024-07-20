package org.aadi.bookstore.catalog.domain;

import org.aadi.bookstore.ApplicationProperties;
import org.aadi.bookstore.catalog.Product;
import org.aadi.bookstore.catalog.ProductService;
import org.aadi.bookstore.common.models.PagedResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repo;
    private final ApplicationProperties props;

    ProductServiceImpl(ProductRepository repo,
                       ApplicationProperties props) {
        this.repo = repo;
        this.props = props;
    }

    public PagedResult<Product> getProducts(int pageNo) {
        Sort sort = Sort.by("name").ascending();
        int page = pageNo <= 1 ? 0 : pageNo - 1;
        Pageable pageable = PageRequest.of(page, props.pageSize(), sort);
        Page<Product> productsPage = repo.findAllBy(pageable);
        return new PagedResult<>(productsPage);
    }

    public Optional<Product> getByCode(String code) {
        return repo.findByCode(code);
    }
}
