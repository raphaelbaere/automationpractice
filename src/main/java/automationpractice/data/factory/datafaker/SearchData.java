package automationpractice.data.factory.datafaker;

import automationpractice.data.dto.SearchDTO;
import automationpractice.utils.DataFakerGenerator;

public class SearchData {
    DataFakerGenerator dataFakerGenerator = new DataFakerGenerator();

    public SearchDTO produtoValido(){
        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setNome("Printed Dress");
        return searchDTO;
    }

    public SearchDTO produtoInvalido(){
        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setNome(dataFakerGenerator.ProdutoFaker());
        return searchDTO;
    }

    public SearchDTO produtoComNomeProximo(){
        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setNome("Pri Dre");
        return searchDTO;
    }


}
