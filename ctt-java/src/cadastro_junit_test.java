import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Test;

import br.com.cadastro.dao.CadastroDAO;
import br.com.cadastro.model.Cadastro;
import br.com.cadatro.factory.ConnectionFactory;



class cadastro_junit_test {
	
	CadastroDAO cadastroDao = new CadastroDAO();
	
	@Test
	public void bANCO() throws Exception {
		Assert.assertNotNull(ConnectionFactory.creatConnectionToMySQL());
	}
	
	@Test
	public void valida() {
		Assert.assertEquals("OK", cadastroDao.getValida());
	}
}

