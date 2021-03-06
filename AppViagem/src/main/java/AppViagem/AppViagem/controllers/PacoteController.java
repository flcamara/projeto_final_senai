package AppViagem.AppViagem.controllers;


import AppViagem.AppViagem.models.Pacote;
import AppViagem.AppViagem.repository.PacoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PacoteController {

    @Autowired
    private PacoteRepository pr;

    // GET que chama o FORM para cadastrar Pacote
    @RequestMapping("/cadastrarPacote")
    public String form() {
        return "pacote/form-pacote";
    }

    // POST que cadastra Pacote
    @RequestMapping(value = "/cadastrarPacote", method = RequestMethod.POST)
    public String form(@Valid Pacote pacote, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/cadastrarPacote";
        }

        pr.save(pacote);
        attributes.addFlashAttribute("success", "Pacote atualizado com sucesso!");
        long idLong = pacote.getId();
        String id = "" + idLong;
        return "redirect:/detalhes-pacote/" + id;
    }

    // GET que lista Pacotes
    @RequestMapping("/pacotes")
    public ModelAndView listaPacotes() {
        ModelAndView mv = new ModelAndView("pacote/lista-pacote");
        Iterable<Pacote> pacotes = pr.findAll();
        mv.addObject("pacotes", pacotes);
        return mv;
    }

    // GET detalhes dos funcionário
    @RequestMapping("/detalhes-pacote/{id}")
    public ModelAndView detalhesPacote(@PathVariable("id") long id) {
        Pacote pacote = pr.findById(id);
        ModelAndView mv = new ModelAndView("pacote/detalhes-pacote");
        mv.addObject("pacotes", pacote);
        return mv;
    }

    // GET que deleta Pacote
    @RequestMapping("/deletarPacote")
    public String deletarPacote(long id) {
        Pacote pacote = pr.findById(id);
        pr.delete(pacote);
        return "redirect:/pacotes";
    }

    // MÉTODOS QUE ATUALIZAM PACOTE

    // Get que chama o FORM de edição do Pacote
    @RequestMapping("/editar-pacote")
    public ModelAndView editarPacote(long id) {
        Pacote pacote = pr.findById(id);
        ModelAndView mv = new ModelAndView("pacote/update-pacote");
        mv.addObject("pacote", pacote);
        return mv;
    }

    // POST que atualiza o Pacote
    @RequestMapping(value = "/editar-pacote", method = RequestMethod.POST)
    public String updatePacote(@Valid Pacote pacote, BindingResult result, RedirectAttributes attributes) {
        pr.save(pacote);
        attributes.addFlashAttribute("success", "Pacote atualizado com sucesso!");
        long idLong = pacote.getId();
        String id = "" + idLong;
        return "redirect:/detalhes-pacote/" + id;
    }
}
