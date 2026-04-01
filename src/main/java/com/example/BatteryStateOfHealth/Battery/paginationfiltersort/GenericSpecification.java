package com.example.BatteryStateOfHealth.Battery.paginationfiltersort;

import com.example.BatteryStateOfHealth.Battery.util.Constants;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class GenericSpecification<T> implements Specification<T> {
    private final List<SearchCriteria> list = new ArrayList<>();

    public void add(SearchCriteria searchCriteria){
        list.add(searchCriteria);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb){
        List<Predicate> predicates = new ArrayList<>();

        for(SearchCriteria criterion : list){
            //Work with joins (nested fields such as department.name
            Path<Object> path = getPath(root, criterion.columnName());

            switch (criterion.operationType()){
                case Constants.CASE_INSENSITIVE_SEARCH ->
                        predicates.add(cb.like(cb.lower(path.as(String.class)),
                                        "%" + criterion.value().toString().toLowerCase() + "%"
                                ));

                case Constants.EQUALITY_SEARCH ->
                        predicates.add(cb.equal(path, criterion.value()));

                case Constants.GREATER_THAN_SEARCH ->
                        predicates.add(cb.greaterThan(path.as(String.class), criterion.value().toString()));
            }
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }

    // Helper to navigate nested properties such as "department.name"
    private Path<Object> getPath(Root<T> root, String fieldToFilter) {
        if (fieldToFilter.contains(".")) {
            String[] partsOfFieldToFilter = fieldToFilter.split("\\.");
            Join<Object, Object> join = root.join(partsOfFieldToFilter[0]);
            return join.get(partsOfFieldToFilter[1]);
        }
        return root.get(fieldToFilter);
    }
}
