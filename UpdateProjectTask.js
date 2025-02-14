import React, { Component } from 'react'
import {connect} from 'react-redux'
import {Link} from 'react-router-dom'
import PropTypes from 'prop-types'
import classnames from 'classnames'
import {getProjectTask,updateProjectTask} from "../../../actions/backlogActions";

 class UpdateProjectTask extends Component {
     constructor(){
        super()
        //const{id1}=this.props.match.params
         this.state={
            "id": "",
            "projectSequence": "",
            "summary": "",
            "acceptanceCriteria": "",
            "status": "",
            "priority": "",
            "dueDate": "",
            "create_At": "" ,
            "projectIdentifir": "",
             errors:{}
         };
         this.onChange=this.onChange.bind(this);
         this.onSubmit=this.onSubmit.bind(this);
     }
     
     componentDidMount(){
         const{backlog_id,pt_id}=this.props.match.params;
         this.props.getProjectTask(backlog_id,pt_id,this.props.history)
     }
     componentWillReceiveProps(nextProps){
        this.setState({errors: nextProps.errors});

    }   
     componentWillReceiveProps(nextProps){
         const{
            id ,
            projectSequence ,
            summary ,
            acceptanceCriteria ,
            status ,
            priority ,
            dueDate ,
            create_At,
            projectIdentifir
           // errors:{} 
         }=nextProps.project_task;
         this.setState({
            id ,
            projectSequence ,
            summary ,
            acceptanceCriteria ,
            status ,
            priority ,
            dueDate ,
            create_At,
            projectIdentifir
            //errors:nextProps.errors

         });
         
     }
     onChange(e){
        this.setState({[e.target.name]:e.target.value})
    }
    onSubmit(e){
        e.preventDefault();

        const   UpdateProjectTask={
            
            id:this.state.id ,
           // projectSequence:this.state.projectSequence ,
            summary:this.state.summary ,
            acceptanceCriteria :this.state.acceptanceCriteria,
            status:this.state.status ,
            priority:this.state.priority ,
            dueDate :this.state.dueDate,
            create_At:this.state.create_At,
            projectIdentifir:this.state.projectIdentifir

        };
        //console.log(UpdateProjectTask)
        this.props.updateProjectTask(this.state.projectIdentifir,this.state.acceptanceCriteria,
            UpdateProjectTask,this.props.history);
    }
    render() {
        const{errors}=this.state;
        
        return (
            <div className="add-PBI">
        <div className="container">
            <div className="row">
                <div className="col-md-8 m-auto">
                    <Link to={`/projectBoard/${this.state.projectIdentifir}`} className="btn btn-light">
                        Back to Project Board
                    </Link>
                    <h4 className="display-4 text-center">Update Project Task</h4>
                    <p className="lead text-center">Project Code:{this.state.projectIdentifir}</p>
                    <form onSubmit={this.onSubmit}>
                        <div className="form-group">
                            <input type="text" className={classnames("form-control form-control-lg ",{
                                "is-invalid":errors.summary
                            })} name="summary" placeholder="Project Task summary" value={this.state.summary}
                            onChange={this.onChange}/>
                             {errors.summary && (
                              <div className="invalid-feedback">{errors.summary}</div> 
                          )}
                        </div>
                        {/* <div className="form-group">
                            <textarea className="form-control form-control-lg" placeholder="Backlog Description" 
                            name="backlogDescription" value={this.state.backlogDescription}  >
                            </textarea>
                        </div> */}
                        <h6>Due Date</h6>
                        <div className="form-group">
                            <input type="date" className="form-control form-control-lg" 
                            name="dueDate" value={this.state.dueDate} onChange={this.onChange}/>
                        </div>
                        <div className="form-group">
                            <select className="form-control form-control-lg" 
                            name="priority" value={this.state.priority} onChange={this.onChange}>
                                <option value={0}>Select Priority</option>
                                <option value={1}>High</option>
                                <option value={2}>Medium</option>
                                <option value={3}>Low</option>
                            </select>
                        </div>

                        <div className="form-group">
                            <select className="form-control form-control-lg"
                             name="status" value={this.state.status} onChange={this.onChange}>
                                <option value="">Select Status</option>
                                <option value="TO_DO">TO DO</option>
                                <option value="IN_PROGRESS">IN PROGRESS</option>
                                <option value="DONE">DONE</option>
                            </select>
                        </div>

                        <input type="submit" className="btn btn-primary btn-block mt-4" />
                    </form>
                </div>
            </div>
        </div>
    </div>
        )
    }
};
UpdateProjectTask.propType={
    getProjectTask:PropTypes.func.isRequired,
    project_task:PropTypes.object.isRequired,
    updateProjectTask:PropTypes.func.isRequired,
    errors:PropTypes.object.isRequired,
}
const mapStateToProps=state=>({
    project_task:state.backlog.project_task,
    errors:state.errors

})

export default connect(mapStateToProps,{getProjectTask,updateProjectTask})
(UpdateProjectTask);