package Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import code.CpuInstanceAllocater;
import code.OptimizedResults;

class CpuTest {
	
	CpuInstanceAllocater cpu=new CpuInstanceAllocater();
	

	
	@Test
	void getCostMethod()
	{
//		cpu=new CpuInstanceAllocater();
		List<OptimizedResults> op=cpu.getCost(24, 100, 0);
		
		assertNotNull(op);
	}
	
	@Test
	void testNoResults()
	{
//		cpu=new CpuInstanceAllocater();
		assertEquals(0, cpu.getCost(24, 100, 5).size());
	}
	
}


