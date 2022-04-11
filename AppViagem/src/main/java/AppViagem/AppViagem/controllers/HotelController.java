package AppViagem.AppViagem.controllers;

import AppViagem.AppViagem.models.Hotel;
import AppViagem.AppViagem.repository.HotelRepository;

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
public class HotelController {

    @Autowired
    private HotelRepository er;

    // GET que chama o FORM que cadastra hotel
    @RequestMapping("/cadastrarHotel")
    public String form() {
        return "hotel/form-hotel";
    }

    // POST que cadastra a hotel
    @RequestMapping(value = "/cadastrarHotel", method = RequestMethod.POST)
    public String form(@Valid Hotel hotel, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verique os campos!");
            return "redirect:/cadastrarHotel";
        }

        er.save(hotel);
        attributes.addFlashAttribute("success", "Hotel atualizada com sucesso!");
        long idLong = hotel.getId();
        String id = "" + idLong;
        return "redirect:/detalhes-hotel/" + id;
    }

    // Get que lista hoteis
    @RequestMapping("/hoteis")
    public ModelAndView listaHoteis() {
        ModelAndView mv = new ModelAndView("hotel/lista-hotel");
        Iterable<Hotel> hoteis = er.findAll();
        mv.addObject("hoteis", hoteis);
        return mv;
    }

    // GET que lista hotel
    @RequestMapping("/detalhes-hotel/{id}")
    public ModelAndView detalhesHotel(@PathVariable("id") long id) {
        Hotel hotel = er.findById(id);
        ModelAndView mv = new ModelAndView("hotel/detalhes-hotel");
        mv.addObject("hoteis", hotel);
        return mv;
    }

    // GET que deleta hotel
    @RequestMapping("/deletarHotel")
    public String deletarHotel(long id) {
        Hotel hotel = er.findById(id);
        er.delete(hotel);
        return "redirect:/hoteis";
    }

    // MÉTODOS QUE ATUALIZAM HOTEL

    // GET que chama o FORM de edição de hotel
    @RequestMapping("/editarHotel")
    public ModelAndView editarHotel(long id) {
        Hotel hotel = er.findById(id);
        ModelAndView mv = new ModelAndView("hotel/update-hotel");
        mv.addObject("hotel", hotel);
        return mv;
    }

    // POST que atualiza a Hotel
    @RequestMapping(value = "/editarHotel", method = RequestMethod.POST)
    public String updateProfissional(@Valid Hotel hotel, BindingResult result, RedirectAttributes attributes) {
        er.save(hotel);
        attributes.addFlashAttribute("success", "Hotel atualizada com sucesso!");
        long idLong = hotel.getId();
        String id = "" + idLong;
        return "redirect:/detalhes-hotel/" + id;
    }
}
