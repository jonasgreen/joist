package features.migrations;

import static joist.domain.migrations.MigrationKeywords.createTable;
import static joist.domain.migrations.MigrationKeywords.foreignKey;
import static joist.domain.migrations.MigrationKeywords.integer;
import static joist.domain.migrations.MigrationKeywords.primaryKey;
import static joist.domain.migrations.MigrationKeywords.varchar;
import joist.domain.migrations.AbstractMigration;

public class m0013 extends AbstractMigration {

    public m0013() {
        super("OneToOneA.");
    }

    public void apply() {
        createTable("one_to_one_a_foo", primaryKey("id"), varchar("name"), integer("version"));
        createTable("one_to_one_a_bar", primaryKey("id"), varchar("name"), foreignKey("one_to_one_a_foo").unique(), integer("version"));

        // not unique merely on the foreign key
        createTable("one_to_one_b_foo", primaryKey("id"), varchar("name"), integer("version"));
        createTable("one_to_one_b_bar", primaryKey("id"), varchar("name"), foreignKey("one_to_one_b_foo"), integer("version"));
        // createUniqueConstraint("one_to_one_b_bar", "name", "one_to_one_b_foo_id");
    }

}
