package david.augusto.luan.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import david.augusto.luan.converter.DozerConverter;
import david.augusto.luan.data.dto.PersonVO;
import david.augusto.luan.data.model.Person;
import david.augusto.luan.exceptions.ResourceNotFoundException;
import david.augusto.luan.repositories.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepo;

	public PersonVO create(PersonVO pDto) {
		Person person = DozerConverter.parseObject(pDto, Person.class);
		return DozerConverter.parseObject(personRepo.saveAndFlush(person), PersonVO.class);
	}

	public List<PersonVO> findAll() {
		return DozerConverter.parseObject(personRepo.findAll(), PersonVO.class);
	}

	public PersonVO findById(Long id) {
		Person person = extracted(id);
		return DozerConverter.parseObject(person, PersonVO.class);
	}

	public void delete(Long id) {
		Person person = extracted(id);
		personRepo.delete(person);
	}

	public PersonVO update(PersonVO pVo) {
		Person updated = personRepo.findById(pVo.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No Person record from id: " + pVo.getId()));
		updated.setFirstName(pVo.getFirstName());
		updated.setLastName(pVo.getLastName());
		updated.setBirth(pVo.getBirth());
		return DozerConverter.parseObject(personRepo.save(updated), PersonVO.class);
	}

	// extracted
	private Person extracted(Long id) {
		Person person = personRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Person no records from id: " + id));
		return person;
	}
}