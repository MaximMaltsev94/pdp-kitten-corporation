package pdp.kitten.corporation.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pdp.kitten.corporation.domain.Department;

@Component
public class DepartmentConverter implements Converter<String, Department> {
    @Override
    public Department convert(String id) {
        return new Department(id, null, null, null);
    }
}
