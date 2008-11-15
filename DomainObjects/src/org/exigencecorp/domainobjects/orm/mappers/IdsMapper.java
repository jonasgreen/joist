package org.exigencecorp.domainobjects.orm.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.exigencecorp.domainobjects.DomainObject;
import org.exigencecorp.domainobjects.queries.Alias;
import org.exigencecorp.jdbc.RowMapper;

public class IdsMapper<T extends DomainObject> implements RowMapper {

    private final Alias<T> from;
    private final List<Integer> ids;

    public IdsMapper(Alias<T> from, List<Integer> ids) {
        this.from = from;
        this.ids = ids;
    }

    public void mapRow(ResultSet rs) throws SQLException {
        this.ids.add(new Integer(rs.getInt(this.from.getIdColumn().getName())));
    }

}
