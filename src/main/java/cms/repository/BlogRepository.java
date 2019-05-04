package cms.repository;

import cms.model.Blog;
import cms.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogRepository extends PagingAndSortingRepository<Blog,Long> {
    Iterable<Blog> findAllByCategory(Category Category);
    Page<Blog> findAllByAuthorContaining(String author, Pageable pageable);
}
