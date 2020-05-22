package free.lance.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Embeddable
public class Doc {
    // Название
    @Column
    private String name;

    // Тип документа
    @Enumerated( EnumType.STRING )
    private DocType docType;

    public Doc( String name, DocType docType ){
        this.name = name;
        this.docType = docType;
    }
}
