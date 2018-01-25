package ind.lgh.system.repository;

import ind.lgh.system.domain.Message;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * Neo4j关系 MessageRepository
 *
 * @author lgh
 * @since 2018-01-25
 */
@Repository
public interface MessageRepository extends GraphRepository<Message> {
}
