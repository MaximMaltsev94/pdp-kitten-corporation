package pdp.kitten.corporation.repository.converter;

import org.apache.commons.collections4.IterableUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@FunctionalInterface
public interface GenericConverter<Source, Target> {

    Target convert(Source source);

    default Target convertOne(Iterable<Source> sources) {
        if(IterableUtils.isEmpty(sources)) {
            return null;
        }

        return this.convert(sources.iterator().next());
    }

    default List<Target> convertMany(Iterable<Source> sources) {
        if(IterableUtils.isEmpty(sources)) {
            return Collections.emptyList();
        }

        List<Target> resultList = new ArrayList<>();
        for (Source source : sources) {
            Target t = convert(source);
            if(t != null) {
                resultList.add(convert(source));
            }
        }
        return resultList;
    }

}
